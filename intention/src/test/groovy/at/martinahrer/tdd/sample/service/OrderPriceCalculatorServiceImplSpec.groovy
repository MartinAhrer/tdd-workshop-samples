package at.martinahrer.tdd.sample.service

import at.martinahrer.tdd.sample.domain.Order
import at.martinahrer.tdd.sample.domain.OrderPosition
import at.martinahrer.tdd.sample.domain.OrderRepository
import spock.lang.Specification

/**
 * This Spock specification's purpose is to demonstrate patterns for writing expressive tests.
 * It also shows that using the right test language and framework are essential to support writing expressive tests.
 * <ol>
 *     <li>Plain Java with JUnit is barely sufficient, though Junit 5 has improved a lot</li
 *     <li>Kotlin does a bit better but still is limited by its static language nature. Adding Kotest as testframework instead of JUnit is a good option</li
 *     <li>Groovy is the best choice because of its dynamic language features. Adding Spock as testframework is the best combo</li
 *  </ol>
 *
 * Further we show how a testing framework can support writing expressive test code
 *  <ol>
 *      <li>Discuss basic features of Groovy</li>
 *      <li>Discuss basic features of Spock framework</li>
 *      <li>Discuss various patterns for expressive tests</li>
 *      <ul>
 *          <li>Use the AAA pattern (arrange, act, assert)</li>
 *          <li>Put focus on expected values and actual values</li>
 *          <li>Put focus variables driving a test and hide others</li>
 *          <li>Move code from the arrange section to factories (that should be shareable within a project, across projects)</li>
 *      </ul>
 *      <li>Discuss test fixtures and how its supported by Gradle </li>
 *      <li>Discuss data driven testing with Spock</li>
 *
 *  </ol>
 */
class OrderPriceCalculatorServiceImplSpec extends Specification {

    OrderRepository orderRepository = Mock()
    OrderPriceCalculatorService calculator = new OrderPriceCalculatorServiceImpl(orderRepository)
    DiscountScheme discountScheme = new DiscountSchemeImpl()

    /**
     * This test does even not try to be expressive (using literal values in many places, not using self explaining variables, ..)
     */
    def "no discount on quantity < 10"() {
        given:
        orderRepository.findById(1) >> {
            // we have lot's of boilerplate code for initialization that leads to less expressive (noisy) test
            Order order = new Order()
            order.setId(1)
            // can you spot the variables that are actually driving the test conditions?
            order.addPosition(new OrderPosition(9, new BigDecimal(100), "Running Shoes", OrderPosition.ArticleGroup.SPORTS))
            Optional.of(order)
        }

        when:
        def result = calculator.invoke(1, discountScheme)

        then:
        result == 900
    }

    /**
     * This test is trying to improve on using variables for arguments driving the test (quantity, price)
     */
    def "discount on quantity > 9"() {
        given:
        def expectedPrice = 900
        def orderId = 1
        def quantity = 10 // <-- drives the test conditions
        def price = 100 // <-- drives the test conditions

        orderRepository.findById(orderId) >> {
            Order order = new Order()
            order.id = orderId
            // leave with no factory usage to show boilerplate that leads to less expressive (noisy) test
            order.addPosition(new OrderPosition(quantity, new BigDecimal(price), "Running Shoes", OrderPosition.ArticleGroup.SPORTS))
            Optional.of(order)
        }

        when:
        def actualPrice = calculator.invoke(orderId, discountScheme)

        then:
        actualPrice == expectedPrice // <-- name variables so that it is clear what is the actual and the expected price
    }

    /**
     * This test is trying to improve further with using data factories.
     * We have removed all of the initialization constants that are not driving (are not relevant for) the test
     * and using local factory methods.
     */
    def "discount on quantity > 9 (with data factory)"() {
        given:
        def expectedPrice = 900
        def orderId = 1
        def quantity = 10 // <-- drives the test
        def price = 100 // <-- drives the test

        orderRepository.findById(orderId) >> Optional.of(newOrder(orderId, newOrderPosition(quantity, price)))

        when:
        def actualPrice = calculator.invoke(orderId, discountScheme)

        then:
        actualPrice == expectedPrice
    }


    /**
     * This test is now refactoring the above test cases into a single data driven test
     * and adds one more test for quantity 100
     *
     * ATTENTION: Looking at the implementation of invoke we may realize we are actually not testing the right thing here.
     * The calculator is actually only applying the discount but it is really only summing up discounted positions.
     * Hence we should add a test for the DiscountScheme.
     * At some moment of being unfocused we obviously implemented some code (the DiscountScheme implementation)
     * not following TDD practices.
     */
    def "discount on quantity #quantity price of #price results in total #expectedPrice"() {
        given:
        def orderId = 1
        orderRepository.findById(orderId) >> Optional.of(newOrder(orderId, newOrderPosition(quantity, price)))

        when:
        def actualPrice = calculator.invoke(orderId, discountScheme)

        then:
        actualPrice == expectedPrice

        where:
        quantity | price | expectedPrice
        9        | 100   | 900
        10       | 100   | 900
        100      | 100   | 9000
    }

    /**
     * Test at the proper abstraction level.
     *
     * Do not test implementation details of external components to the class under test.
     * I.e do not test the behaviour of an external component, a component used by the class under test.
     * As this component may change in future it may be required that you need to update this test
     * (and other tests that may also follow this bad practice)
     *
     * For tests we also need to follow the Single Responsibility Principle (see SOLID principles).
     * A test should have only one reason to change, this is when the system under test is changed.
     * A test should have only one reason to fail, this is when the system under test fails.
     *
     * With this it should be clear that we also must avoid asserting on details that are exclusively driven by the
     * external component. All these details should be tested by the test related to the external component.
     *
     * Not obeying this principle will likely surface when you see yourself updating tests when referenced components
     * change their implementation.
     *
     * This test's focus is now basically on summing up somehow discounted and un-discounted positions.
     * The earlier test would have broken when the discount algorithm changed and returned different discount rates for the assumed quantities.
     *
     * A failing test should indicate that the system under test is broken but should not break just because an external system is broken.
     * This is important e.g. when we start to differentiate unit tests and integration tests
     * It is the reason why we should have many well designed unit tests but less integration tests.
     *
     */
    def "eligible positions are discounted and summed"() {
        given:
        def orderId = 1
        def quantity = 5
        def price = 10

        def position1 = newOrderPosition(quantity, price)
        def position2 = newOrderPosition(quantity * 2, price)
        orderRepository.findById(orderId) >> Optional.of(newOrder(orderId, position1, position2))
        discountScheme = Mock {
            calculateDiscountRate(quantity) >> BigDecimal.valueOf(0.0)
            calculateDiscountRate(quantity * 2) >> BigDecimal.valueOf(0.1)
        }

        when:
        def actualPrice = calculator.invoke(orderId, discountScheme)

        then:
        def expectedPrice = position1.totalPrice() + position2.totalPrice() * BigDecimal.valueOf(0.9)
        actualPrice == expectedPrice
    }

    private Order newOrder(int orderId, OrderPosition... orderPosition) {
        Order order = new Order()
        order.id = orderId
        // leave with no factory usage to show boilerplate that leads to less expressive (noisy) test

        order.addPosition(orderPosition.flatten())
    }

    private OrderPosition newOrderPosition(int quantity, int price) {
        new OrderPosition(quantity, new BigDecimal(price), "Running Shoes", OrderPosition.ArticleGroup.SPORTS)
    }

}
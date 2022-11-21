package at.martinahrer.tdd.sample.domain.impl;

import at.martinahrer.tdd.sample.domain.*;
import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

public class SlowAndFlakyInMemoryOrderRepository implements OrderRepository {

    @Override
    public Optional<Order> findById(Long id) {
        if (id == 1L) {
            simulateDatabaseQueryDelay();
            simulateDatabaseQueryFail();
            Order order = newOrder(id);
            return Optional.of(order);
        }
        return Optional.empty();
    }


    private static void simulateDatabaseQueryDelay() {
        try {
            Thread.sleep(RandomUtils.nextInt(5, 25));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void simulateDatabaseQueryFail() {
        if (System.getenv("NO_FLAKYNESS") == null) {
            var remainder = RandomUtils.nextInt(1, 100) % 7;
            if (remainder == 0) {
                throw new RuntimeException("The database query could not be executed. Seems the database is down!");
            }
        }
    }


    // tag::factory-methods[]
    public static Order newOrder(Long id) {
        var quantity = 5;
        var price = 10;

        var position1 = newOrderPosition(quantity, price);
        var position2 = newOrderPosition(quantity * 2, price);

        Order order = newOrder(id, position1, position2);
        return order;
    }

    static Order newOrder(long orderId, OrderPosition... orderPosition) {
        Order order = new Order();
        Customer customer = new Customer();
        Address address = new Address();
        address.setLine1("Line 1");
        address.setLine2("Line 2");
        address.setCity("City");
        address.setZip("Zip");
        address.setCountry("Country");
        customer.setAddress(address);
        order.setCustomer(customer);
        order.setId(orderId);
        order.addPosition(Arrays.stream(orderPosition).toList());
        return order;
    }

    static OrderPosition newOrderPosition(int quantity, int price) {
        return new OrderPosition(quantity, new BigDecimal(price), "Running Shoes", OrderPosition.ArticleGroup.SPORTS);
    }

// end::factory-methods[]

}

package at.martinahrer.tdd.samples.service

import spock.lang.Specification


class DiscountSchemeImplSpec extends Specification {

    def "discount #expectedDiscountRate based on quantity #quantity"() {
        given:
        DiscountScheme discountScheme = new DiscountSchemeImpl()

        when:
        def actualDiscountRate = discountScheme.calculateDiscountRate(quantity)

        then:
        actualDiscountRate == expectedDiscountRate

        where:
        quantity | expectedDiscountRate
        1        | BigDecimal.valueOf(0.0)
        9        | BigDecimal.valueOf(0.0)
        10       | BigDecimal.valueOf(0.1)
        100      | BigDecimal.valueOf(0.1)
    }

}
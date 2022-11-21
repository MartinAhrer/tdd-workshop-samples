package at.martinahrer.tdd.sample.domain.testfixture

import at.martinahrer.tdd.sample.domain.Order

class OrderFactory(private val customerFactory: CustomerFactory = CustomerFactory()) :
    DomainObjectFactory<Order> {
    override fun create(): Order {
        val order = Order()
        order.id = 1
        order.customer = customerFactory.create()

        return order
    }
}
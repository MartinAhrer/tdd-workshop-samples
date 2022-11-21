package at.martinahrer.tdd.sample.domain.testfixture

import at.martinahrer.tdd.sample.domain.Customer

class CustomerFactory(private val addressFactory: AddressFactory = AddressFactory()) : DomainObjectFactory<Customer> {
    override fun create(): Customer {
        val customer = Customer()
        customer.name = "Name"
        customer.address = addressFactory.create()
        return customer
    }
}
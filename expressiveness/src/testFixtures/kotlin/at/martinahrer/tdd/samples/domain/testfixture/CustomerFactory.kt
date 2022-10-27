package at.martinahrer.tdd.samples.domain.testfixture

import at.martinahrer.tdd.samples.domain.Customer

class CustomerFactory(private val addressFactory: AddressFactory = AddressFactory()) : DomainObjectFactory<Customer> {
    override fun create(): Customer {
        val customer = Customer()
        customer.name = "Name"
        customer.address = addressFactory.create()
        return customer
    }
}
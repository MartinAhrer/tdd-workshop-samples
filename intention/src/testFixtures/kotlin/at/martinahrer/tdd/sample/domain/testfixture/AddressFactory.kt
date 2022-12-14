package at.martinahrer.tdd.sample.domain.testfixture

import at.martinahrer.tdd.sample.domain.Address

class AddressFactory() :
    DomainObjectFactory<Address> {
    override fun create(): Address {
        val address = Address()
        address.line1 = "Line 1"
        address.line2 = "Line 2"
        address.city = "City"
        address.zip = "zip"
        address.country = "Country"
        return address
    }

}
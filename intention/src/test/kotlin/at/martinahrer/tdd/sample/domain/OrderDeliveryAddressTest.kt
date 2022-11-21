package at.martinahrer.tdd.sample.domain

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


/**
 * Test that demonstrates how modifying an implementation detail can break 100's of tests and cause an update of all that tests.
 * Notice that the 'arrange' section of the test requires quite a lot of lines of code for doing the test initialization.
 * This is a major distraction when trying to understand the code.
 * Consider that this test is quite simple and in the real world may require even more initialization.
 *
 * Update {@link Address#printAddress} and uncomment the commented line to change the implementation of address formatting to break the tests.
 *
 * Attention: this test has a serious issue about "testing the right thing".
 */
class OrderDeliveryAddressTest {
    companion object {
        private const val expectedLine1 = "Line 1"
        private const val expectedLine2 = "Line 2"
        private const val expectedCity = "City"
        private const val expectedZip = "zip"
    }

    @Test
    fun `a order delivery address must contain all address components`() {
        //arrange
        val address = Address()

        address.line1 = expectedLine1
        address.line2 = expectedLine2
        address.city = expectedCity
        address.zip = expectedZip

        val customer = Customer()
        customer.name = "Name"
        customer.address = address

        val order = Order()
        order.customer = customer

        // act
        val actualResult = order.printDeliveryLabel()

        // assert
        assertTrue(actualResult.contains(expectedLine1))
        assertTrue(actualResult.contains(expectedLine2))
        assertTrue(actualResult.contains(expectedCity))
        assertTrue(actualResult.contains(expectedZip))
    }
}
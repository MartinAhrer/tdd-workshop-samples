package at.martinahrer.tdd.samples.domain

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


/**
 * Test that demonstrates how modifying an implementation detail can break 100's of tests and cause an update of all that tests'
 */
class TestOrderProcessing {
    companion object {
        private const val expectedLine1 = "Line 1"
        private const val expectedLine2 = "Line 2"
        private const val expectedCity = "City"
        private const val expectedZip = "zip"

        @Test
        fun testOrderProcessing() {
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
            val actualResult = order.deliver()

            // assert
            assertTrue(actualResult.contains(expectedLine1))
            assertTrue(actualResult.contains(expectedLine2))
            assertTrue(actualResult.contains(expectedCity))
            assertTrue(actualResult.contains(expectedZip))
        }
    }

}
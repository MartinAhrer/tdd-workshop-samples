package at.martinahrer.tdd.samples.domain

import at.martinahrer.tdd.samples.domain.testfixture.OrderFactory
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


class TestOrderProcessingWithFactory {
    companion object {
        @Test
        fun testOrderProcessing(): Unit {
            //arrange
            val order = OrderFactory().create()
            val expectedLine1 = order.customer.address.line1
            val expectedLine2 = order.customer.address.line2
            val expectedCity = order.customer.address.city
            val expectedZip = order.customer.address.zip

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
package at.martinahrer.tdd.sample.domain

import at.martinahrer.tdd.sample.domain.testfixture.OrderFactory
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


/**
 * This test has tremendously reduced the amount of code for initializing the test.
 * The reader of the code should be able to grasp the test intention quite fast.
 *
 * Attention: this test has a serious issue about "testing the right thing".
 */
class OrderDeliveryAddressTestWithFactory {
    @Test
    fun `a order delivery address must contain all address components`(): Unit {
        //arrange
        val order = OrderFactory().create()

        val expectedLine1 = order.customer.address.line1
        val expectedLine2 = order.customer.address.line2
        val expectedCity = order.customer.address.city
        val expectedZip = order.customer.address.zip

        // act
        val actualResult = order.printDeliveryLabel()

        // assert
        assertTrue(actualResult.contains(expectedLine1))
        assertTrue(actualResult.contains(expectedLine2))
        assertTrue(actualResult.contains(expectedCity))
        assertTrue(actualResult.contains(expectedZip))
    }
}
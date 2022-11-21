package at.martinahrer.tdd.sample.domain

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.IntStream
import java.util.stream.Stream

class OrderDeliveryAddressTestWithFactorySuite {

    @TestFactory
    fun `test order delivery address suite (with factory)`(): Stream<DynamicTest> {
        return IntStream.range(0, 100)
            .mapToObj { n ->
                DynamicTest.dynamicTest("test order processing ${n}", OrderDeliveryAddressTestWithFactory()::`a order delivery address must contain all address components`)
            }
    }
}
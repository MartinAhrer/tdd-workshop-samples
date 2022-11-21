package at.martinahrer.tdd.sample.domain

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.IntStream
import java.util.stream.Stream

class OrderDeliveryAddressTestSuite {

    @TestFactory
    fun `test order delivery address suite`(): Stream<DynamicTest> {
        return IntStream.range(0, 100)
            .mapToObj { n ->
                DynamicTest.dynamicTest("test order processing ${n}", OrderDeliveryAddressTest()::`a order delivery address must contain all address components`)
            }
    }
}
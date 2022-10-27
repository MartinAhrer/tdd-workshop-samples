package at.martinahrer.tdd.samples.domain

import at.martinahrer.tdd.samples.domain.TestOrderProcessing.Companion.testOrderProcessing
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.IntStream
import java.util.stream.Stream

class TestOrderProcessingSuite {

    @TestFactory
    fun `test order processing suite`(): Stream<DynamicTest> {
        return IntStream.range(0, 100)
            .mapToObj { n ->
                DynamicTest.dynamicTest("test order processing ${n}", ::testOrderProcessing)
            }
    }
}
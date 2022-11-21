package at.martinahrer.tdd.sample.service;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * This class simulates a suite of tests where each tests a service the depends on a repository.
 * That repository is an instance of a real implementation that like a query of a database is slow and can fail.
 * So the test is a test of the category integration test.
 *
 * The intention of this test is to show that it adds no value to implement tests that use real implementations
 * for dependencies of the class under test.
 * Discuss why this adds no value:
 * <ul>
 *     <li>External dependencies can introduce flaky-ness</li>
 *     <li>External dependencies slow down a test</li>
 *     <li>External dependencies can produce false failing test results</li>
 * </ul>
 */
public class OrderServiceDeliveryAddressTestSuite {

    @TestFactory
    public Stream<DynamicTest> testOrderServiceSuite() {
        return IntStream.range(0, 100)
                .mapToObj(n -> dynamicTest("test order service " + n, () -> new OrderServiceDeliveryAddressTest().testOrderDeliveryAddress()));
    }
}

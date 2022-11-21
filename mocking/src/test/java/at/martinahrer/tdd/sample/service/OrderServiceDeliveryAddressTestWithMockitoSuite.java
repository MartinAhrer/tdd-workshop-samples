package at.martinahrer.tdd.sample.service;

import at.martinahrer.tdd.sample.domain.OrderRepository;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * This class simulates a suite of tests where each tests a service the depends on a repository.
 * That repository is a mock that unlike a query of a database is not slow.
 * So compared to {@link OrderServiceDeliveryAddressTestSuite} the test is now a test of the category unit test as external dependencies are mocked
 * and the class under test is tested isolated from its external dependencies.
 *
 * As a result the test is fast and an external dependency is not able to introduce a false failing test.
 * Hence, we have a test that reliably reports on the state of the class under test.
 * It is isolated from side effects that may be introduced by the external dependency.
 */
@ExtendWith(MockitoExtension.class)
public class OrderServiceDeliveryAddressTestWithMockitoSuite {

    @Mock
    OrderRepository orderRepository;

    @TestFactory
    public Stream<DynamicTest> testOrderDeliveryAddress() {
        Executable e;
        return IntStream.range(0, 100)
                .mapToObj(n -> dynamicTest("test order service " + n, () -> {
                    OrderServiceDeliveryAddressTestWithMockito test = new OrderServiceDeliveryAddressTestWithMockito();
                    test.orderRepository = orderRepository;
                    test.testOrderDeliveryAddress();
                }));
    }
}

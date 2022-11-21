package at.martinahrer.tdd.sample.service;

import at.martinahrer.tdd.sample.domain.impl.SlowAndFlakyInMemoryOrderRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This test has the following issues regarding dependencies of the class under test:
 * <ul>
 *     <li>no test data factory, we only rely on the data fetched through the repository which is not a setup that is fully controlled by this test</li>
 *     <li>the class under test directly interacts with a dependent component </li>
 *     <ul>
 *         <li>That is slow as a repository usually does some database access</li>
 *         <li>That may be broken</li>
 *     </ul>
 *     <li>With every (unnecessary integration test) test that we add the test runtime increases</li>
 *     <li>In case the dependent component breaks we have every integration test failing even when the test class under test in fact is ok</li>
 *     <li>See test pyramid versus ice cone (and what happens when its hot - you get dirty fingers)</li>
 * </ul>
 * {@see <a href="https://martinfowler.com/articles/practical-test-pyramid.html">The Practical Test Pyramid</a>}
 * <br/>
 * {@see <a href="https://martinfowler.com/bliki/TestPyramid.html">Test Pyramid</a>}
 * <p>
 * We have another issue. Are we testing the right thing? Have a closer look at the implementation that creates the delivery address for printing.
 */

public class OrderServiceDeliveryAddressTest {

    @Test
    public void testOrderDeliveryAddress() {
        assertTrue(true);
    }
}

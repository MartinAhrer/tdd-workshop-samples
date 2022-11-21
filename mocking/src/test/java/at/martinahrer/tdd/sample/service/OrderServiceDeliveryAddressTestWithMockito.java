package at.martinahrer.tdd.sample.service;

import at.martinahrer.tdd.sample.domain.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import at.martinahrer.tdd.sample.domain.impl.Assertions;

import static at.martinahrer.tdd.sample.domain.impl.Assertions.assertAddressLabel;
import static at.martinahrer.tdd.sample.domain.impl.SlowAndFlakyInMemoryOrderRepository.newOrder;

/**
 * This test improves {@link OrderServiceDeliveryAddressTest} by mocking external dependencies.
 * Also, it improves on expressing intent by using a reusable assertion method {@link Assertions#assertAddressLabel}
 */
@ExtendWith(MockitoExtension.class)
public class OrderServiceDeliveryAddressTestWithMockito {

    @Mock
    OrderRepository orderRepository;

    @Test
    public void testOrderDeliveryAddress() {
        //given
        var orderId = 1L;

        Mockito.lenient()
                .when(orderRepository.findById(orderId))
                .thenReturn(Optional.of(newOrder(orderId)));

        OrderService orderService = new OrderServiceImpl(orderRepository);

        //when
        String actualResult = orderService.printDeliveryLabel(orderId);

        //then
        assertAddressLabel(actualResult, "City", "Zip", "Line 1", "Line 2");
    }
}

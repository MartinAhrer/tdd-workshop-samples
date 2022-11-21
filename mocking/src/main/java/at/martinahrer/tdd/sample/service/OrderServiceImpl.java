package at.martinahrer.tdd.sample.service;

import at.martinahrer.tdd.sample.domain.OrderRepository;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public String printDeliveryLabel(long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(IllegalArgumentException::new)
                .printDeliveryLabel();
    }
}

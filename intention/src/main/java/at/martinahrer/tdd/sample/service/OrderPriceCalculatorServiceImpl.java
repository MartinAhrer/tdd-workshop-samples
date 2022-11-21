package at.martinahrer.tdd.sample.service;

import at.martinahrer.tdd.sample.domain.Order;
import at.martinahrer.tdd.sample.domain.OrderRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderPriceCalculatorServiceImpl implements OrderPriceCalculatorService {

    private OrderRepository orderRepository;

    public OrderPriceCalculatorServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public BigDecimal invoke(long orderNumber, DiscountScheme discountScheme) {
        Order order = orderRepository.findById(orderNumber).orElseThrow(IllegalArgumentException::new);
        return order.getPositions().stream()
                .map(p -> {
                    BigDecimal discountRate = discountScheme.calculateDiscountRate(p.getQuantity()).setScale(2, RoundingMode.DOWN);
                    return p.totalPrice()
                            .subtract(p.totalPrice().multiply(discountRate))
                            .setScale(0, RoundingMode.UP);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

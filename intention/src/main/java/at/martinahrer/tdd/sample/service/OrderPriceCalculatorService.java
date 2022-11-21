package at.martinahrer.tdd.sample.service;

import java.math.BigDecimal;

public interface OrderPriceCalculatorService {
    BigDecimal invoke(long orderNumber, DiscountScheme discountScheme);
}

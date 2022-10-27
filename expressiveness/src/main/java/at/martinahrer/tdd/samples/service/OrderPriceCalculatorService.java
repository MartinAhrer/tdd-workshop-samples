package at.martinahrer.tdd.samples.service;

import java.math.BigDecimal;

public interface OrderPriceCalculatorService {
    BigDecimal invoke(long orderNumber, DiscountScheme discountScheme);
}

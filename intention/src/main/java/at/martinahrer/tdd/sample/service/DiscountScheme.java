package at.martinahrer.tdd.sample.service;

import java.math.BigDecimal;

public interface DiscountScheme {
    BigDecimal calculateDiscountRate(int quantity);
}

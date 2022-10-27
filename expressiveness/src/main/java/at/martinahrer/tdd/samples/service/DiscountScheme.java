package at.martinahrer.tdd.samples.service;

import java.math.BigDecimal;

public interface DiscountScheme {
    BigDecimal calculateDiscountRate(int quantity);
}

package at.martinahrer.tdd.samples.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

public class DiscountSchemeImpl implements DiscountScheme {
    @Override
    public BigDecimal calculateDiscountRate(int quantity) {
        SortedMap<Integer, BigDecimal> map=new TreeMap<>();
        map.put(9, BigDecimal.valueOf(0.0));
        map.put(10, BigDecimal.valueOf(0.1));
        Optional<Integer> first = map.keySet().stream()
                .sorted()
                .filter(k -> k >= quantity)
                .findFirst();
        return first.isPresent() ? map.get(first.get()) : map.values().stream().skip(map.size() -1L).findFirst().orElse(BigDecimal.ZERO);
    }
}

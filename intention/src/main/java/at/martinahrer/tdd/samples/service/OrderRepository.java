package at.martinahrer.tdd.samples.service;

import at.martinahrer.tdd.samples.domain.Order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(Long id);
}

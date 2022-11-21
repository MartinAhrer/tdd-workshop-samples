package at.martinahrer.tdd.sample.domain;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(Long id);
}

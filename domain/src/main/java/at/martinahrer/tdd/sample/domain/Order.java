package at.martinahrer.tdd.sample.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class Order {
    private Long id;
    private Customer customer;

    private List<OrderPosition> positions=new ArrayList<>();

    public String printDeliveryLabel() {
        Address address = customer.getAddress();
        return "Deliver to " + address.printAddress() ;
    }

    public Order addPosition(OrderPosition orderPosition) {
        positions.add(orderPosition);
        return this;
    }
    public Order addPosition(Collection<OrderPosition> orderPositions) {
        positions.addAll(orderPositions);
        return this;
    }

}
package at.martinahrer.tdd.sample.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {
    private String name;
    private Address address;
}

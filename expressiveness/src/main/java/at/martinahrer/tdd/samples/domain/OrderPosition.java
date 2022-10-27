package at.martinahrer.tdd.samples.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderPosition {

    enum ArticleGroup {
        SPORTS,
        TOYS
    }
    int quantity;
    BigDecimal price;
    String articleName;
    ArticleGroup articleGroup;

    public BigDecimal totalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}

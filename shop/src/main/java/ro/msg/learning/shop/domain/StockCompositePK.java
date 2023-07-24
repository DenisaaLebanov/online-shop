package ro.msg.learning.shop.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
@AllArgsConstructor
public class StockCompositePK implements Serializable {

    @ManyToOne
    private Product product;

    @ManyToOne
    private Location location;


}

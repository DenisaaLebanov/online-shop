package ro.msg.learning.shop.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class StockCompositePK implements Serializable {

    @ManyToOne
    private Product product;

    @ManyToOne
    private Location location;


}

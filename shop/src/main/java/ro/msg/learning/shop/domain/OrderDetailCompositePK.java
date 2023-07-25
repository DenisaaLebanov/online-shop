package ro.msg.learning.shop.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OrderDetailCompositePK implements Serializable {

    @ManyToOne
    private PlacedOrder placedOrder;

    @ManyToOne
    private Product product;


}

package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@Entity
@Table(name="OrderDetail")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OrderDetail {

    @EmbeddedId
    private OrderDetailCompositePK orderDetailCompositePK;

    @ManyToOne
    private Location shippedFrom;

    private Integer quantity;
}

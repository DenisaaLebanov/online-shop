package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="Stock")
@AllArgsConstructor
public class Stock {

    @EmbeddedId
    private StockCompositePK stockCompositePK;

    private Integer quantity;
}
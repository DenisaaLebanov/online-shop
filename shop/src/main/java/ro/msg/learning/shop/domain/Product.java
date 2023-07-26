package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table (name="Product")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends EntityBase{

    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private String imageUrl;

    @ManyToOne(cascade = {CascadeType.ALL})
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "stockCompositePK.product")
    private List<Stock> stockList;

    @OneToMany(mappedBy = "orderDetailCompositePK.product")
    private List<OrderDetail> orderDetailList;
}

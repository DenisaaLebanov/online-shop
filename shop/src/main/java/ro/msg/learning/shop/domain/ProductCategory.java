package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "ProductCategory")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends EntityBase{

    private String categoryName;
    private String categoryDescription;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> productList;
}

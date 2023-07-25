package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.domain.Product;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryDto {

    private UUID categoryId;
    private String categoryName;
    private String productDescription;
}

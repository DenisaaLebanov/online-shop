package ro.msg.learning.shop.mapper;

import lombok.Builder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.dto.ProductDto;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class ProductMapper {

    public ProductCategoryDto toProductCategoryDto(ProductCategory productCategory){
//        UUID id = productCategory.getId();
//        String name = productCategory.getName();
//        String description = productCategory.getDescription();
//        return new ProductCategoryDto(id, name, description);

        return ProductCategoryDto.builder()
                .categoryId(productCategory.getId())
                .categoryName(productCategory.getCategoryName())
                .productDescription(productCategory.getCategoryDescription())
                .build();
    }
    public ProductDto toProductDto(Product product, ProductCategory productCategory){

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .weight(product.getWeight())
                .imageUrl(product.getImageUrl())
                .productCategoryDto(toProductCategoryDto(productCategory))
                .build();
    }

    public ProductCategory fromDtoToProductCategory(ProductCategoryDto productCategoryDto){
        //return new ProductCategory(productCategoryDto.getCategoryId(), productCategoryDto.getCategoryName(), productCategoryDto.getProductDescription());

        return ProductCategory.builder()
                .id(productCategoryDto.getCategoryId())
                .categoryName(productCategoryDto.getCategoryName())
                .categoryDescription(productCategoryDto.getProductDescription())
                .build();
    }

    public Product fromDtoToProduct(ProductDto productDto,ProductCategoryDto productCategoryDto){
//        return new Product(productDto.getId(), productDto.getName(), productDto.getDescription(), productDto.getPrice(), productDto.getWeight(),
//                            productDto.getImageUrl(), )

        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .weight(productDto.getWeight())
                .imageUrl(productDto.getImageUrl())
                .productCategory(fromDtoToProductCategory(productCategoryDto))
                .build();
    }
}

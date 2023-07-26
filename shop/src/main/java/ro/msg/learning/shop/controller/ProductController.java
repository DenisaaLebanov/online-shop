package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.mapper.ProductMapper;
import ro.msg.learning.shop.service.ProductCategoryService;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/shop/product")
@RestController
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID id) {
        Product product = productService.getProductById(id);
        ProductCategory productCategory = productCategoryService.getProductCategoryById(product.getProductCategory().getId());
        ProductDto productDto = productMapper.toProductDto(product, productCategory);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDtoParam, ProductCategoryDto productCategoryDtoParam) {
        ProductCategory productCategory = productMapper.fromDtoToProductCategory(productCategoryDtoParam);
        Product product = productMapper.fromDtoToProduct(productDtoParam, productCategoryDtoParam);
        productCategoryService.createProductCategory(productCategory);
        productService.createProduct(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable UUID id,
                                              @RequestBody ProductDto productDtoParam
                                              ){
        Product product = productMapper.fromDtoToProduct(productDtoParam, productDtoParam.getProductCategoryDto());
        productService.updateProduct(product, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable UUID id){
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll(){
        List<Product> productList = productService.getAll();
        List<ProductDto> productDtoList = productList.stream().map(e -> productMapper.toProductDto(e, e.getProductCategory())).toList();
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }
}

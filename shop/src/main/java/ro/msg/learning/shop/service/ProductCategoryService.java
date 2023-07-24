package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;

import java.util.UUID;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;


    public ProductCategory getProductCategoryById(UUID id){
        return productCategoryRepository.getReferenceById(id);
    }

    public ProductCategory createProductCategory(ProductCategory productCategory){ return productCategoryRepository.save(productCategory); }

    public ProductCategory updateProductCategory(ProductCategory productCategory){ return productCategoryRepository.save(productCategory); }
}

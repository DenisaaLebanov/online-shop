package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockService stockService;

    public Product getProductById(UUID id){
        return productRepository.getReferenceById(id);
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Product product, UUID id) {
        return productRepository.findById(id)
                .map(product1 -> {
                    product1.setName(product.getName());
                    product1.setDescription(product.getDescription());
                    product1.setPrice(product.getPrice());
                    product1.setWeight(product.getWeight());
                    product1.setImageUrl(product.getImageUrl());
                    product1.setProductCategory(product.getProductCategory());
                    return productRepository.save(product1);
                })
                .orElseGet(() -> {
                    product.setId(id);
                    return productRepository.save(product);
                });
    }

    public void deleteById(UUID id) {
        if (productRepository.existsById(id))
            productRepository.deleteById(id);
        else throw new IllegalStateException("Id does not exists!");
    }

    public List<Product> getAll(){
       return productRepository.findAll();
    }

//    public boolean productExists(Product product){
//        boolean exists = stockService.findById(product.getId());
//    }
}

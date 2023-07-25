package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.StockCompositePK;
import ro.msg.learning.shop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
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

    public Stock getStock(StockCompositePK stockCompositePK) {
        return stockService.findById(stockCompositePK);
    }
}

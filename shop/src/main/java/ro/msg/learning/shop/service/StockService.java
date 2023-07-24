package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.repository.StockRepository;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;
//
//    public Stock findById(Product product){
//        stockRepository.findById(product.getId());
//    }
}

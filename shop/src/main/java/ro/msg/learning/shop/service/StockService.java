package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.StockCompositePK;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Stock findById(StockCompositePK stockCompositePK){
        return stockRepository.getReferenceById(stockCompositePK);
    }

    public Optional<Stock> updateStock(Integer quantity, StockCompositePK stockCompositePK){
        Integer newStock = findById(stockCompositePK).getQuantity() - quantity;
        return stockRepository.findById(stockCompositePK)
                .map(stock -> {
                    stock.setQuantity(newStock);
                    stock.setStockCompositePK(stockCompositePK);
                    return stockRepository.save(stock);
                });
    }
}

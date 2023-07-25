package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.repository.OrderDetailRepository;

import java.util.*;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductService productService;

    public void getAvailableLocations(OrderDetail orderDetail, HashMap<Location, Integer> locations){
        UUID productId = orderDetail.getOrderDetailCompositePK().getProduct().getId();
        Objects.requireNonNull(productId);
        Product product = productService.getProductById(productId);
        List<Stock> stockList = product.getStockList();
        for (Stock stockEntry: stockList) {
            if (stockEntry.getQuantity() >= orderDetail.getQuantity()) {
                if (!locations.containsKey(stockEntry.getStockCompositePK().getLocation()))
                    locations.put(stockEntry.getStockCompositePK().getLocation(), 1);
                else{
                    Integer value = locations.get(stockEntry.getStockCompositePK().getLocation());
                    locations.put(stockEntry.getStockCompositePK().getLocation(),
                            value + 1);
                }
            }
        }
    }

    public void createOrderDetail(OrderDetail orderDetail){
        orderDetailRepository.save(orderDetail);
    }
}
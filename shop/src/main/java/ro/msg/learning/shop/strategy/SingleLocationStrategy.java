package ro.msg.learning.shop.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.service.ProductService;

import java.util.*;

@RequiredArgsConstructor
public class SingleLocationStrategy implements OrderStrategyInterface {

    private final ProductService productService;

    public Location findKeys(HashMap<Location, Integer> locationHashMap, Integer numberOfProducts) {
        return locationHashMap
                .entrySet()
                .stream()
                .filter(e -> Objects.equals(e.getValue(), numberOfProducts))
                .map(Map.Entry::getKey)
                .reduce((first, second) -> first)
                .orElse(null);
    }

    public List<OrderStrategyObject> getAvailableLocations(List<OrderDetail> orderDetailList, HashMap<Location, Integer> locations) {
        List<OrderStrategyObject> orderStrategyList = new ArrayList<>();

        for (OrderDetail orderDetailEntry : orderDetailList) {
            UUID productId = orderDetailEntry.getOrderDetailCompositePK().getProduct().getId();
            Objects.requireNonNull(productId);
            Product product = productService.getProductById(productId);
            List<Stock> stockList = product.getStockList();
            for (Stock stockEntry : stockList) {
                if (stockEntry.getQuantity() >= orderDetailEntry.getQuantity()) {
                    if (!locations.containsKey(stockEntry.getStockCompositePK().getLocation()))
                        locations.put(stockEntry.getStockCompositePK().getLocation(), 1);
                    else {
                        Integer value = locations.get(stockEntry.getStockCompositePK().getLocation());
                        locations.put(stockEntry.getStockCompositePK().getLocation(),
                                value + 1);
                    }
                }
            }
        }
        Location location = findKeys(locations, orderDetailList.size());

        if (location == null)
            throw new IllegalStateException("Order cannot be placed!");

        for (OrderDetail orderDetailEntry : orderDetailList) {
            OrderStrategyObject orderStrategyObject = new OrderStrategyObject(location, orderDetailEntry.getOrderDetailCompositePK().getProduct(), orderDetailEntry.getQuantity());
            orderStrategyList.add(orderStrategyObject);
        }
        return orderStrategyList;
    }
}
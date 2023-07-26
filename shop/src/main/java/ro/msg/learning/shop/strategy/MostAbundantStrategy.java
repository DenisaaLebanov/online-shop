package ro.msg.learning.shop.strategy;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.OrderStrategyObject;
import ro.msg.learning.shop.service.ProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public class MostAbundantStrategy implements OrderStrategyInterface {

    private final ProductService productService;

    @Override
    public List<OrderStrategyObject> getAvailableLocations(List<OrderDetail> orderDetailList, HashMap<Location, Integer> locations) {
        return new ArrayList<>();
    }
}

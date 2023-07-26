package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.OrderStrategyObject;

import java.util.HashMap;
import java.util.List;


public interface OrderStrategyInterface {
    List<OrderStrategyObject> getAvailableLocations(List<OrderDetail> orderDetailList, HashMap<Location, Integer> locations);
}

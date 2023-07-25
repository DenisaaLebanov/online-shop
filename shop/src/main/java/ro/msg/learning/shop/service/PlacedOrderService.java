package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.repository.PlacedOrderRepository;

import java.security.KeyStore;
import java.util.*;

@Service
public class PlacedOrderService {

    @Autowired
    private PlacedOrderRepository placedOrderRepository;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private StockService stockService;

    private Location findKeys(HashMap<Location, Integer> hashMap, Integer value){
        return hashMap
                .entrySet()
                .stream()
                .filter(e -> Objects.equals(e.getValue(), value))
                .map(Map.Entry::getKey)
                .reduce((first, second) -> first)
                .orElse(null);
    }

    public void createPlacedOrder(PlacedOrder placedOrder){
        HashMap<Location, Integer> locationHashMap = new HashMap<>();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for(OrderDetail orderDetail: placedOrder.getOrderDetailList()){
            orderDetailList.add(orderDetail);
        }

        for(OrderDetail orderDetail: orderDetailList){
             orderDetailService.getAvailableLocations(orderDetail, locationHashMap);
        }

        if (locationHashMap.containsValue(orderDetailList.size())) {
            placedOrderRepository.save(placedOrder);
            for(OrderDetail orderDetail: orderDetailList) {
                OrderDetailCompositePK orderDetailCompositePK = new OrderDetailCompositePK(placedOrder, orderDetail.getOrderDetailCompositePK().getProduct());

                Location location = findKeys(locationHashMap, orderDetailList.size());
                StockCompositePK stockCompositePK = new StockCompositePK(orderDetail.getOrderDetailCompositePK().getProduct(), location);
                stockService.updateStock(orderDetail.getQuantity(), stockCompositePK);

                orderDetail.setOrderDetailCompositePK(orderDetailCompositePK);
                orderDetail.setShippedFrom(location);
                orderDetailService.createOrderDetail(orderDetail);
            }
        }
        else  throw new IllegalStateException("Order cannot be placed!");
    }
}
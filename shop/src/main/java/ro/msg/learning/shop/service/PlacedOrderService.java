package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.repository.PlacedOrderRepository;
import ro.msg.learning.shop.strategy.OrderStrategyInterface;
//import ro.msg.learning.shop.strategy.OrderStrategyInterface;

import java.util.*;

@Service
public class PlacedOrderService {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private PlacedOrderRepository placedOrderRepository;

    @Autowired
    private final OrderStrategyInterface orderStrategy;

    @Autowired
    private StockService stockService;

    public PlacedOrderService(OrderStrategyInterface orderStrategy) {
        this.orderStrategy = orderStrategy;
    }

    public void createPlacedOrder(PlacedOrder placedOrder){
        HashMap<Location, Integer> locationHashMap = new HashMap<>();
        List<OrderStrategyObject> orderStrategyObjectList = new ArrayList<>();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        List<OrderDetailCompositePK> orderDetailCompositePKList = new ArrayList<>();
        for(OrderDetail orderDetail: placedOrder.getOrderDetailList())
            orderDetailList.add(orderDetail);

        orderStrategyObjectList = orderStrategy.getAvailableLocations(orderDetailList, locationHashMap);

        placedOrderRepository.save(placedOrder);

        for (OrderStrategyObject orderStrategyEntry: orderStrategyObjectList){
            OrderDetailCompositePK orderDetailCompositePK = new OrderDetailCompositePK(placedOrder, orderStrategyEntry.getProduct());
            orderDetailCompositePKList.add(orderDetailCompositePK);
            StockCompositePK stockCompositePK = new StockCompositePK(orderStrategyEntry.getProduct(), orderStrategyEntry.getLocation());
            stockService.updateStock(orderStrategyEntry.getQuantity(), stockCompositePK);
            orderDetailList.get(orderStrategyObjectList.indexOf(orderStrategyEntry)).setOrderDetailCompositePK(orderDetailCompositePK);
            orderDetailList.get(orderStrategyObjectList.indexOf(orderStrategyEntry)).setShippedFrom(orderStrategyObjectList.get(0).getLocation());
            orderDetailService.createOrderDetail(orderDetailList.get(orderStrategyObjectList.indexOf(orderStrategyEntry)));
        }
    }
}
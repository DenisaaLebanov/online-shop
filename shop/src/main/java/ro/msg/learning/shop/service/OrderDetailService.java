package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.repository.OrderDetailRepository;


@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public void createOrderDetail(OrderDetail orderDetail){
        orderDetailRepository.save(orderDetail);
    }

}
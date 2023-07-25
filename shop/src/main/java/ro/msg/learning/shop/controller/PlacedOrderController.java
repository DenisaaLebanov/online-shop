package ro.msg.learning.shop.controller;

import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.PlacedOrder;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.ProductQuantityDto;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.service.PlacedOrderService;

@RequestMapping("/shop/order")
@Validated
@RestController
public class PlacedOrderController {

    @Autowired
    private PlacedOrderService placedOrderService;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<Void> createPlacedOrder(@RequestBody OrderDto orderDto){
        PlacedOrder placedOrder = orderMapper.fromDtoToPlacedOrder(orderDto);
        placedOrderService.createPlacedOrder(placedOrder);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

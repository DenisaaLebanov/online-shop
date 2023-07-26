package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.dto.ProductQuantityDto;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.service.OrderDetailService;

@RequestMapping("/shop/orderDetail")
@AllArgsConstructor
@NoArgsConstructor
@RestController
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody ProductQuantityDto productQuantityDto){
        OrderDetail orderDetail = orderMapper.fromDtoToOrderDetail(productQuantityDto);
        orderDetailService.createOrderDetail(orderDetail);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

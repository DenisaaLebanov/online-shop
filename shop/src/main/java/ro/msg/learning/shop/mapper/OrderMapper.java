package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.dto.AddressDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.ProductQuantityDto;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Component
public class OrderMapper {

    public Product fromProductQuantityDtoToProduct(ProductQuantityDto productQuantityDto){
        return Product.builder()
                .id(productQuantityDto.getId())
                .build();
    }

    public OrderDetailCompositePK productQuantityDtoToEntity(ProductQuantityDto productQuantityDto){
       return OrderDetailCompositePK.builder()
                                .product(fromProductQuantityDtoToProduct(productQuantityDto))
                                .build();
    }

    public OrderDetail fromDtoToOrderDetail(ProductQuantityDto productQuantityDto){
        return OrderDetail.builder()
                .quantity(productQuantityDto.getQuantity())
                .orderDetailCompositePK(productQuantityDtoToEntity(productQuantityDto))
                .build();
    }

    public Address fromDtoToAddress(AddressDto addressDto){
        return Address.builder()
                .country(addressDto.getCountry())
                .county(addressDto.getCounty())
                .city(addressDto.getCity())
                .streetAddress(addressDto.getStreetAddress())
                .build();
    }

    public PlacedOrder fromDtoToPlacedOrder(OrderDto orderDto){
        return PlacedOrder.builder()
                .createdAt(orderDto.getCreatedAt())
                .address(fromDtoToAddress(orderDto.getAddress()))
                .orderDetailList(orderDto.getProductQuantityList().stream().map(this::fromDtoToOrderDetail).toList())
                .build();
    }
}
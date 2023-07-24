package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.domain.Address;
import ro.msg.learning.shop.domain.Location;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private LocalDateTime createdAt;
//    private String country;
//    private String county;
//    private String city;
//    private String streetAddress;
    private AddressDto address;
    private List<ProductQuantityDto> productQuantityList;
}

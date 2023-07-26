package ro.msg.learning.shop.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Embeddable
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String country;
    private String county;
    private String city;
    private String streetAddress;
}

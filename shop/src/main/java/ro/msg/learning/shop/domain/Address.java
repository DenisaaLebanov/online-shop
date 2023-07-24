package ro.msg.learning.shop.domain;

import jakarta.persistence.Embeddable;
import lombok.experimental.SuperBuilder;

@Embeddable
@SuperBuilder
public class Address {
    private String country;
    private String county;
    private String city;
    private String streetAddress;
}

package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
@Table(name="Location")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "identifier", column = @Column(name="VIN"))
public class Location extends EntityBase{

    private String name;

    @OneToMany(mappedBy = "stockCompositePK.location")
    private List<Stock> stockList;

    @OneToMany(mappedBy = "shippedFrom")
    private List<OrderDetail> orderDetailList;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name= "address.country", column = @Column(name = "address_country")),
            @AttributeOverride(name= "address.county", column = @Column(name = "address_county")),
            @AttributeOverride(name= "address.city", column = @Column(name = "address_city")),
            @AttributeOverride(name= "address.street", column = @Column(name = "address_street"))
    })
    private Address address;

}

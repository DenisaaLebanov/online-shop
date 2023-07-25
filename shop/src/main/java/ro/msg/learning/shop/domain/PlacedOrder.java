package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name="PlacedOrder")
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@SuperBuilder
@AttributeOverride(name = "identifier", column = @Column(name="VIN"))
public class PlacedOrder extends EntityBase{

    @ManyToOne
    private Costumer costumer;

    @OneToMany(mappedBy = "orderDetailCompositePK.placedOrder")
    private List<OrderDetail> orderDetailList;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name= "address.country", column = @Column(name = "address_country")),
            @AttributeOverride(name= "address.county", column = @Column(name = "address_county")),
            @AttributeOverride(name= "address.city", column = @Column(name = "address_city")),
            @AttributeOverride(name= "address.street", column = @Column(name = "address_street"))
    })
    private Address address;

    private LocalDateTime createdAt;
}

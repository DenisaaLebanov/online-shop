package ro.msg.learning.shop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name="Costumer")
@AllArgsConstructor
@NoArgsConstructor

public class Costumer extends EntityBase{

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "costumer")
    private List<PlacedOrder> placedOrderList;
}

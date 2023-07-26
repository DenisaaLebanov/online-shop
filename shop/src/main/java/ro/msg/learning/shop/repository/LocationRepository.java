package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.domain.Location;

import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

    @Query("SELECT l FROM Location l JOIN Stock s on l.id = s.stockCompositePK.product.id WHERE " +
            "s.stockCompositePK.product.id = 1? AND s.quantity >= 2? ORDER BY s.quantity DESC LIMIT 1")
    Location getLocationWithMaxStock(UUID productId, Integer quantity);
}

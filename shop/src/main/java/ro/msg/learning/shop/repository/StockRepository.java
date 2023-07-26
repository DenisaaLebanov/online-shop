package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.StockCompositePK;

import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockCompositePK> {
}

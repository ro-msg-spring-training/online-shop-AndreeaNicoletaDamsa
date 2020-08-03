package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.ShopOrder;

public interface ShopOrderRepository extends JpaRepository<ShopOrder, Integer> {
}

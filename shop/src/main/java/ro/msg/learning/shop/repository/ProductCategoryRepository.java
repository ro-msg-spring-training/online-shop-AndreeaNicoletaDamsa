package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entity.Product;

@Repository
public interface ProductCategoryRepository extends JpaRepository<Product, Integer> {
}

package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.ProductCategory;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}

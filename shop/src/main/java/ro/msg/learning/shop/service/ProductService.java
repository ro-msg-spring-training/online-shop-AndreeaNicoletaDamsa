package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.Product;

import java.util.List;

public interface ProductService {
    void save(Product product);
    void delete(Product product);
    Product update(Product product);
    Product findById(Integer id);
    List<Product> findAll();
}

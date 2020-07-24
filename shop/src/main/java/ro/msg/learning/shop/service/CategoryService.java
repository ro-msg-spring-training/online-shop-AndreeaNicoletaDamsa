package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.ProductCategory;

public interface CategoryService {
    void save(ProductCategory productCategory);
    ProductCategory findById(Integer id);
}

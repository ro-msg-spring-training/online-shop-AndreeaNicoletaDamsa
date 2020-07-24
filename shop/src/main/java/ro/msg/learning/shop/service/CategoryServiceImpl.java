package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;

import javax.transaction.Transactional;

@Service
public class CategoryServiceImpl  implements CategoryService{

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Transactional
    @Override
    public void save(ProductCategory productCategory) {

        productCategoryRepository.findById(productCategory.getId()).ifPresentOrElse(foundProduct ->
        {throw new IllegalArgumentException("This product already exists");},()->productCategoryRepository.save(productCategory));
    }

    @Override
    public ProductCategory findById(Integer id) {
        return productCategoryRepository.findById(id).get();
    }
}

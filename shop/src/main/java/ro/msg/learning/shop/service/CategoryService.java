package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ProductCategoryRepository productCategoryRepository;


    public void save(ProductCategory productCategory) {

        productCategoryRepository.findById(productCategory.getId()).ifPresentOrElse(foundProduct ->
        {
            throw new IllegalArgumentException("This product already exists");
        }, () -> productCategoryRepository.save(productCategory));
    }


    public ProductCategory findById(Integer id) throws Exception {
        Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(id);
        if (productCategoryOptional.isEmpty()) {
            throw new Exception("There is no category with id " + id);
        } else {
            return productCategoryOptional.get();
        }
    }
}

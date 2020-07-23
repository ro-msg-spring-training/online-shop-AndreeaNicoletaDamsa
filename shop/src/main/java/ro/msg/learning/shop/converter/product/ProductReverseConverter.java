package ro.msg.learning.shop.converter.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.SupplierRepository;

@Component
public class ProductReverseConverter {

    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public Product convert(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getProductId());
        product.setName(productDto.getProductName());
        product.setDescription(productDto.getProductDescription());
        product.setWeight(productDto.getWeight());
        product.setSupplier(supplierRepository.findById(productDto.getSupplierId()).get());
        product.setProductCategory(productCategoryRepository.findById(productDto.getCategoryId()).get());
        product.setPrice(productDto.getPrice());
        product.setImageURL(productDto.getImageURL());
        return product;
    }
}

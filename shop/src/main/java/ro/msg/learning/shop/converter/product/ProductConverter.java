package ro.msg.learning.shop.converter.product;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.SupplierRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {

    public ProductDto convertFromProductToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.getName());
        productDto.setProductDescription(product.getDescription());
        productDto.setCategoryDescription(product.getProductCategory().getDescription());
        productDto.setCategoryName(product.getProductCategory().getName());
        productDto.setProductId(product.getId());
        productDto.setPrice(product.getPrice());
        productDto.setWeight(product.getWeight());
        productDto.setSupplierName(product.getSupplier().getName());
        productDto.setImageURL(product.getImageURL());
        productDto.setCategoryId(product.getProductCategory().getId());
        productDto.setSupplierId(product.getSupplier().getId());
        return productDto;
    }

    public List<ProductDto> convertAllFromProduct(List<Product> products) {
        List<ProductDto> productDtos = products.stream().map(product -> this.convertFromProductToDto(product)).collect(Collectors.toList());
        return productDtos;
    }

    public Product convertFromDtoToProduct(ProductDto productDto, SupplierRepository supplierRepository, ProductCategoryRepository productCategoryRepository) {
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

package ro.msg.learning.shop.converter.product;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {

   public ProductDto convert(Product product){
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

    public List<ProductDto> convertAll(List<Product> products){
       List<ProductDto> productDtos = products.stream().map(product -> this.convert(product)).collect(Collectors.toList());
       return productDtos;
    }
}

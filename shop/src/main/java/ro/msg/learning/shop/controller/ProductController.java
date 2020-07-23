package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.converter.product.ProductConverter;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductConverter productConverter;

    @GetMapping("/products/{id}")
    public ProductDto getProduct(@PathVariable Integer id){
        Product product = productService.findById(id);
        return productConverter.convert(product);
    }
}

package ro.msg.learning.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.converter.product.ProductConverter;
import ro.msg.learning.shop.converter.product.ProductReverseConverter;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private ProductReverseConverter productReverseConverter;

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/products/{id}")
    public ProductDto getProduct(@PathVariable Integer id){
        Product product = productService.findById(id);
        ProductDto productDto = productConverter.convert(product);
        System.out.println(productDto.toString());
        return productConverter.convert(product);
    }

    @GetMapping("/products")
    public List<ProductDto> getProducts(){
        return productConverter.convertAll(productService.findAll());
    }

    @PostMapping(path = "/products", consumes = "application/json", produces = "application/json")
    public void addProduct(@RequestBody ProductDto productDto){
        Product product=productReverseConverter.convert(productDto);
        productService.save(productReverseConverter.convert(productDto));
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Integer id){
        Product product = productService.findById(id);
        productService.delete(product);
    }


}

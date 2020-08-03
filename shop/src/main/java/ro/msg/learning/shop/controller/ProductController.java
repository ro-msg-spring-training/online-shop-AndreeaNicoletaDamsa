package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.converter.product.ProductConverter;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.SupplierRepository;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;


    @GetMapping("/products/{id}")
    public ProductDto getProduct(@PathVariable Integer id) {
        Product product = productService.findById(id);
        return productConverter.convertFromProductToDto(product);
    }

    @GetMapping("/products")
    public List<ProductDto> getProducts() {
        return productConverter.convertAllFromProduct(productService.findAll());
    }

    @PostMapping(path = "/products", consumes = "application/json", produces = "application/json")
    public void addProduct(@RequestBody ProductDto productDto) {
        Product product = productConverter.convertFromDtoToProduct(productDto, supplierRepository, productCategoryRepository);
        productService.save(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        Product product = productService.findById(id);
        productService.delete(product);
    }


}

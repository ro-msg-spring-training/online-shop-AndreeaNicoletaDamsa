package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.service.CategoryService;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(path = "/categories", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductCategory addCategory(@RequestBody ProductCategory productCategory) {
        categoryService.save(productCategory);
        return productCategory;
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<ProductCategory> getCategory(@PathVariable Integer id) {
        ProductCategory productCategory=null;
        try {
             productCategory = categoryService.findById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productCategory, HttpStatus.OK);
    }
}

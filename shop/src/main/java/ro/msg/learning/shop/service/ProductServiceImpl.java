package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public void save(Product product) {
        productRepository.findById(product.getId()).ifPresentOrElse(foundProduct ->
        {throw new IllegalArgumentException("This product already exists");},()->productRepository.save(product) );
    }

    @Transactional
    @Override
    public void delete(Product product) {
        productRepository.findById(product.getId()).ifPresentOrElse(foundProduct -> {
            productRepository.delete(product);
        }, () -> {
            throw new NoSuchElementException("The specified element does not exist.");
        });
    }

    @Transactional
    public Product update(Product product) {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if (optionalProduct.isPresent()){
                        Product product1= optionalProduct.get();
                        product1.setName(product.getName());
                        product1.setDescription(product.getName());
                        product1.setImageURL(product.getImageURL());
                        product1.setPrice(product.getPrice());
                        product1.setProductCategory(product.getProductCategory());
                        product1.setSupplier(product.getSupplier());
                        product1.setWeight(product.getWeight());
                        productRepository.save(product1);
                        return product1;

                }
                else { throw new IllegalArgumentException("The update cannot be done");}
    }

    @Override
    public Product findById(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) { throw new IllegalArgumentException("There is no product with given id"); }
        return optionalProduct.get();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}

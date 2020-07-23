package ro.msg.learning.shop.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.entity.Supplier;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.SupplierRepository;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepository repository, ProductCategoryRepository productCategoryRepository, SupplierRepository supplierRepository, ProductRepository productRepository) {

        return args -> {
            log.info("Preloading " + repository.save(new Customer("Bilbo","Ddf" ,"burglar","pass","p.p@gmail.com")));
            log.info("Preloading " + repository.save(new Customer("Frodo","Baggins", "thief","pass","g.g@gamil.com")));
            ProductCategory productCategory = new ProductCategory("sds","adad");
            Supplier supplier = new Supplier("supplier");
            productCategoryRepository.save(productCategory);
            supplierRepository.save(supplier);
            Product product = new Product("name","decp",(float)2.3,1.4,productCategory,supplier,"imageurl");
            productRepository.save(product);
        };
    }
}

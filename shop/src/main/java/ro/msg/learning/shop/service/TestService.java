package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.repository.*;

@Service
public class TestService {
    @Autowired
    private ShopOrderRepository shopOrderRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShopOrderService shopOrderService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public void populate() {
        Address address = new Address("f", "ff", "fff");
        Location location = new Location("numelocatie", address);
        locationRepository.save(location);

        Customer customer = new Customer("w", "h", "t", "v", "r");
        customerRepository.save(customer);

        Location location1 = new Location("location1", address);
        locationRepository.save(location1);
        Location location2 = new Location("location2", address);
        locationRepository.save(location2);

        Product product1 = new Product("nume", "desciere", (float) 3.4, 2.6, null, null, "img");
        product1.setId(1);
        Product product2 = new Product("nume2", "desciere2", (float) 3.4, 2.6, null, null, "img");
        product2.setId(2);
        productRepository.save(product1);
        productRepository.save(product2);

        Stock stock11 = new Stock(product1, location1, 10);
        Stock stock12 = new Stock(product2, location2, 10);
        Stock stock21 = new Stock(product1, location1, 10);
        stockRepository.save(stock11);
        stockRepository.save(stock12);
        stockRepository.save(stock21);
    }


    public void populateForFailStock() {
        Address address = new Address("f", "ff", "fff");
        Location location = new Location("numelocatie", address);
        locationRepository.save(location);

        Customer customer = new Customer("w", "h", "t", "v", "r");
        customerRepository.save(customer);

        Location location1 = new Location("location1", address);
        locationRepository.save(location1);
        Location location2 = new Location("location2", address);
        locationRepository.save(location2);

        Product product1 = new Product("nume", "desciere", (float) 3.4, 2.6, null, null, "img");
        product1.setId(1);
        Product product2 = new Product("nume2", "desciere2", (float) 3.4, 2.6, null, null, "img");
        product2.setId(2);
        productRepository.save(product1);
        productRepository.save(product2);

        Stock stock11 = new Stock(product1, location1, 9);
        Stock stock12 = new Stock(product2, location2, 8);
        Stock stock21 = new Stock(product1, location1, 7);
        stockRepository.save(stock11);
        stockRepository.save(stock12);
        stockRepository.save(stock21);
    }


    public void clear() {
        locationRepository.deleteAll();
        productCategoryRepository.deleteAll();
        productRepository.deleteAll();
        customerRepository.deleteAll();
        shopOrderRepository.deleteAll();
        stockRepository.deleteAll();
        shopOrderRepository.deleteAll();
    }
}

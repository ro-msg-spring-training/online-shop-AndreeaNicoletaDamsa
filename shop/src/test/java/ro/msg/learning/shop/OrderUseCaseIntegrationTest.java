package ro.msg.learning.shop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.dto.ShopOrderDto;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.ShopOrderService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Profile("test")
class OrderUseCaseIntegrationTest {

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

    public void setupSuccess() {

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

    void setupFail(){
        Address address = new Address("f", "ff", "fff");
        Location location = new Location("numelocatie", address);
        locationRepository.save(location);

        Customer customer = new Customer("w", "h", "t", "v", "r");
        customerRepository.save(customer);

        Location location1 = new Location("location1", address);
        locationRepository.save(location1);

        Product product1 = new Product("nume", "desciere", (float) 3.4, 2.6, null, null, "img");
        product1.setId(1);
        Product product2 = new Product("nume2", "desciere2", (float) 3.4, 2.6, null, null, "img");
        product2.setId(2);
        productRepository.save(product1);
        productRepository.save(product2);

        Stock stock11 = new Stock(product1, location1, 10);
        Stock stock21 = new Stock(product1, location1, 10);
        stockRepository.save(stock11);
        stockRepository.save(stock21);
    }

    @Test
    void getAnExistingOrderThroughAllLayers() throws Exception {
        setupSuccess();
        Address address = new Address("f", "ff", "fff");
        List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
        orderDetailDtos.add(new OrderDetailDto(1, 10));
        orderDetailDtos.add(new OrderDetailDto(2, 10));
        ShopOrderDto shopOrderDto = new ShopOrderDto(LocalDateTime.now(), address.getCity(), address.getCountry(), address.getStreetAddress(), orderDetailDtos);

        ShopOrder shopOrder1 = shopOrderService.createOrder(shopOrderDto);
        assertThat(shopOrder1.getOrderDetails().size() == shopOrderDto.getOrderDetailDtos().size());

    }

    @Test
    void missingStockFail(){
        setupFail();
        Address address = new Address("f", "ff", "fff");
        List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
        orderDetailDtos.add(new OrderDetailDto(1, 10));
        orderDetailDtos.add(new OrderDetailDto(2, 10));
        ShopOrderDto shopOrderDto = new ShopOrderDto(LocalDateTime.now(), address.getCity(), address.getCountry(), address.getStreetAddress(), orderDetailDtos);
        int exception = 0;
        try {
            ShopOrder shopOrder1 = shopOrderService.createOrder(shopOrderDto);
        }catch (ResponseStatusException e){
            exception =1;
        }
        if (exception == 0){
            assert(false);
        }
    }



}
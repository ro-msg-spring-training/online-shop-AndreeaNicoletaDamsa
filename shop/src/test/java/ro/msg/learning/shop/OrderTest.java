package ro.msg.learning.shop;

import org.aspectj.weaver.ast.Or;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.dto.SelectedProductDto;
import ro.msg.learning.shop.dto.ShopOrderDto;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.ShopOrderRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.ShopOrderService;
import ro.msg.learning.shop.service.ShopOrderServiceImpl;
import ro.msg.learning.shop.service.StockService;
import ro.msg.learning.shop.service.StockServiceImpl;
import ro.msg.learning.shop.strategy.LocationStrategy;
import ro.msg.learning.shop.strategy.MostAbundant;
import ro.msg.learning.shop.strategy.SingleLocation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderTest {

    @InjectMocks
    private MostAbundant locationStrategyMostAbundant;

    @InjectMocks
    private SingleLocation locationStrategySingleLocation;

    private StockService stockService = new StockServiceImpl();

    @Mock
    private ShopOrderRepository shopOrderRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private ProductRepository productRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void singleLocationStrategyTest() throws Exception{
        Address address = new Address("sqw", "qw", "qw");
        List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
        orderDetailDtos.add(new OrderDetailDto(1,10));
        orderDetailDtos.add(new OrderDetailDto(2,10));

        ShopOrderDto shopOrderDto = new ShopOrderDto(LocalDateTime.now(), address.getCity(),address.getCountry(),address.getStreetAddress(),orderDetailDtos);

        Location location1= new Location("location1",address);
        Location location2 = new Location("location2", address);
        List<Location> locations = new ArrayList<>();
        locations.add(location1);
        locations.add(location2);

        Product product1 = new Product("nume","desciere",(float)3.4,2.6,null,null,"img");
        product1.setId(1);
        Product product2 = new Product("nume2","desciere2",(float)3.4,2.6,null,null,"img");
        product2.setId(2);
        when(productRepository.findById(1)).thenReturn(java.util.Optional.of(product1));
        when(productRepository.findById(2)).thenReturn(java.util.Optional.of(product2));

        Stock stock11 = new Stock(product1,location1,30);
        Stock stock12 = new Stock(product2, location2,30);
        Stock stock21 = new Stock(product1, location1,50);
        List<Stock> stocks = new ArrayList<Stock>();
        stocks.add(stock11);
        stocks.add((stock12));
        location1.setStocks(stocks);
        List<Stock> stocks1 = new ArrayList<>();
        stocks1.add(stock21);
        location2.setStocks(stocks1);
        when(locationRepository.findAll()).thenReturn(locations);
        List<SelectedProductDto> selectedProductDtos = locationStrategySingleLocation.select(shopOrderDto);
        assertThat(selectedProductDtos.get(0).getLocation().getName().equals(selectedProductDtos.get(1).getLocation().getName()));
        assertThat(selectedProductDtos.get(0).getQuantity() <= selectedProductDtos.get(0).getLocation().getStocks().get(0).getQuantity());
    }

    @Test
    public void mostAbundantStrategyTest() throws Exception{
        Address address = new Address("sqw", "qw", "qw");
        List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
        orderDetailDtos.add(new OrderDetailDto(1,10));
        orderDetailDtos.add(new OrderDetailDto(2,10));

        ShopOrderDto shopOrderDto = new ShopOrderDto(LocalDateTime.now(), address.getCity(),address.getCountry(),address.getStreetAddress(),orderDetailDtos);

        Location location1= new Location("location1",address);
        Location location2 = new Location("location2", address);
        List<Location> locations = new ArrayList<>();
        locations.add(location1);
        locations.add(location2);

        Product product1 = new Product("nume","desciere",(float)3.4,2.6,null,null,"img");
        product1.setId(1);
        Product product2 = new Product("nume2","desciere2",(float)3.4,2.6,null,null,"img");
        product2.setId(2);
        when(productRepository.findById(1)).thenReturn(java.util.Optional.of(product1));
        when(productRepository.findById(2)).thenReturn(java.util.Optional.of(product2));

        Stock stock11 = new Stock(product1,location1,30);
        Stock stock12 = new Stock(product2, location2,30);
        Stock stock21 = new Stock(product1, location1,50);
        List<Stock> stocks = new ArrayList<Stock>();
        stocks.add(stock11);
        stocks.add((stock12));
        location1.setStocks(stocks);
        List<Stock> stocks1 = new ArrayList<>();
        stocks1.add(stock21);
        location2.setStocks(stocks1);
        when(locationRepository.findAll()).thenReturn(locations);
        List<SelectedProductDto> selectedProductDtos = locationStrategyMostAbundant.select(shopOrderDto);
        assertThat(!selectedProductDtos.get(0).getLocation().getName().equals(selectedProductDtos.get(1).getLocation().getName()));
    }


}

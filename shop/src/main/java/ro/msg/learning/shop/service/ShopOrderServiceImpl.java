package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.dto.SelectedProductDto;
import ro.msg.learning.shop.dto.ShopOrderDto;
import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.entity.OrderDetail;
import ro.msg.learning.shop.entity.ShopOrder;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.ShopOrderRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.strategy.LocationStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ShopOrderServiceImpl implements ShopOrderService {


    private LocationStrategy locationStrategy;
    private StockRepository stockRepository;
    private StockService stockService;
    private ShopOrderRepository shopOrderRepository;

    public ShopOrderServiceImpl(LocationStrategy locationStrategy, StockRepository stockRepository, StockService stockService, ShopOrderRepository shopOrderRepository) {
        this.locationStrategy = locationStrategy;
        this.stockRepository = stockRepository;
        this.stockService = stockService;
        this.shopOrderRepository = shopOrderRepository;
    }

    @Override
    public ShopOrder createOrder(ShopOrderDto shopOrderDto) {
        List<SelectedProductDto> selectedProductDtos=null;
        try {
            selectedProductDtos = locationStrategy.select(shopOrderDto);
        }catch(NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        List<Stock> stocks = stockRepository.findAll();
         for (SelectedProductDto selectedProductDto: selectedProductDtos){
             List<Stock> stocks1 = stocks.stream().filter(stock -> stock.getProduct().getId() == selectedProductDto.getProduct().getId() && stock.getLocation().getId() == selectedProductDto.getLocation().getId()).collect(Collectors.toList());
             stocks1.get(0).setQuantity(stocks1.get(0).getQuantity() - selectedProductDto.getQuantity());
             stockService.update(stocks1.get(0));
         }
        ShopOrder shopOrder = new ShopOrder();
         shopOrder.setAddress( new Address(shopOrderDto.getCity(), shopOrderDto.getCountry(), shopOrderDto.getStreetAddress()));
         shopOrder.setCreation_time(shopOrderDto.getCreation_time());
         shopOrder.setCustomer(null);
         shopOrder.setLocation(null);
         List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
         for (SelectedProductDto selectedProductDto: selectedProductDtos){
             orderDetails.add(new OrderDetail(shopOrder,selectedProductDto.getProduct(), selectedProductDto.getQuantity()));
         }
         shopOrder.setOrderDetails(orderDetails);
         shopOrderRepository.save(shopOrder);

        return shopOrder;
    }

    @Override
    public ShopOrder findById(Integer id) {
        return shopOrderRepository.findById(id).get();
    }
}

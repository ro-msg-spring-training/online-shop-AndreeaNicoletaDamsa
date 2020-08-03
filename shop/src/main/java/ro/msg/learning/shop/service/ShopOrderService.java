package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopOrderService {


    private final LocationStrategy locationStrategy;
    private final StockRepository stockRepository;
    private final StockService stockService;
    private final ShopOrderRepository shopOrderRepository;

    public ShopOrder createOrder(ShopOrderDto shopOrderDto) {
        List<SelectedProductDto> selectedProductDtos = null;
        try {
            selectedProductDtos = locationStrategy.select(shopOrderDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        List<Stock> stocks = stockRepository.findAll();
        for (SelectedProductDto selectedProductDto : selectedProductDtos) {
            List<Stock> stocks1 = stocks.stream().filter(stock -> stock.getProduct().getId() == selectedProductDto.getProduct().getId() && stock.getLocation().getId() == selectedProductDto.getLocation().getId()).collect(Collectors.toList());
            stocks1.get(0).setQuantity(stocks1.get(0).getQuantity() - selectedProductDto.getQuantity());
            stockService.update(stocks1.get(0));
        }
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setAddress(new Address(shopOrderDto.getCity(), shopOrderDto.getCountry(), shopOrderDto.getStreetAddress()));
        shopOrder.setCreationTime(shopOrderDto.getCreationTime());
        shopOrder.setCustomer(null);
        shopOrder.setLocation(null);
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (SelectedProductDto selectedProductDto : selectedProductDtos) {
            orderDetails.add(new OrderDetail(shopOrder, selectedProductDto.getProduct(), selectedProductDto.getQuantity()));
        }
        shopOrder.setOrderDetails(orderDetails);
        shopOrderRepository.save(shopOrder);

        return shopOrder;
    }

    public ShopOrder findById(Integer id) throws Exception {
        Optional<ShopOrder> shopOrderOptional = shopOrderRepository.findById(id);
        if (shopOrderOptional.isEmpty()) {
            throw new Exception("There is no order with id " + id);
        } else {
            return shopOrderOptional.get();
        }
    }
}

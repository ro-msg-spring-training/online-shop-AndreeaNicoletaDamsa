package ro.msg.learning.shop.strategy;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.dto.SelectedProductDto;
import ro.msg.learning.shop.dto.ShopOrderDto;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SingleLocation implements LocationStrategy {

    private ProductRepository productRepository;
    private LocationRepository locationRepository;

    public SingleLocation(ProductRepository productRepository, LocationRepository locationRepository) {
        this.productRepository = productRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<SelectedProductDto> select(ShopOrderDto shopOrderDto) throws Exception {
        List<Location> locations = locationRepository.findAll();
        Location singleLoaction = null;
        for (Location location : locations) {
            List<OrderDetailDto> orderDetailDtos = shopOrderDto.getOrderDetailDtos();
            boolean badLoc = false;
            for (OrderDetailDto orderDetailDto : orderDetailDtos) {
                if (!isElement(location.getStocks(), orderDetailDto)) {
                    badLoc = true;
                    break;
                }
            }
            if (badLoc == false){
                singleLoaction = location;
            }
        }
        if (singleLoaction == null) {
            throw new Exception("There is no such location!");
        } else {
            final Location finalLocation = singleLoaction;
            return shopOrderDto.getOrderDetailDtos().stream().map(orderDetailDto -> new SelectedProductDto(productRepository.findById(orderDetailDto.getId()).get(), finalLocation, orderDetailDto.getQuantity())).collect(Collectors.toList());
        }

    }

    public boolean isElement(List<Stock> stocks, OrderDetailDto orderDetailDto) {
        List<Stock> stocks1 = stocks.stream().filter(stock -> (stock.getProduct().getId() == orderDetailDto.getId()) && (stock.getQuantity() >= orderDetailDto.getQuantity())).collect(Collectors.toList());
        return !stocks1.isEmpty();
    }
}

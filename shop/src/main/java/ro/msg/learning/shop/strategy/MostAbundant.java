package ro.msg.learning.shop.strategy;

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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MostAbundant implements LocationStrategy {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<SelectedProductDto> select(ShopOrderDto shopOrderDto) {
        List<SelectedProductDto> finalList= new ArrayList<SelectedProductDto>();
        for (OrderDetailDto orderDetailDto: shopOrderDto.getOrderDetailDtos()){
            Location location = getLocation(orderDetailDto);
            if (location != null){
                finalList.add( new SelectedProductDto(productRepository.findById(orderDetailDto.getId()).get(),location, orderDetailDto.getQuantity()));
            }
            else
            {
                throw new RuntimeException("The product with id"+orderDetailDto.getId()+" cannot be found anywhere!");
            }
        }
        return finalList;
    }

    public Location getLocation(OrderDetailDto orderDetailDto){
        Location finalLocation= null;
        int max = 0;
        List<Location> allLocations = locationRepository.findAll();
        for (Location location: allLocations){
            if (isElementWithMaxStock(location.getStocks(), orderDetailDto,max)){
                finalLocation = location;
            }
        }
        return finalLocation;
    }
    public boolean isElementWithMaxStock(List<Stock> stocks, OrderDetailDto orderDetailDto, Integer max) {
        List<Stock> stocks1 = stocks.stream().filter(stock -> (stock.getProduct().getId() == orderDetailDto.getId()) && (stock.getQuantity() >= orderDetailDto.getQuantity()) && (stock.getQuantity() > max)).collect(Collectors.toList());
        return !stocks1.isEmpty();
    }
}

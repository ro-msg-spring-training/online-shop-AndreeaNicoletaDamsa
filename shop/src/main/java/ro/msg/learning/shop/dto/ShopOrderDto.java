package ro.msg.learning.shop.dto;

import lombok.Data;
import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.OrderDetail;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ShopOrderDto {

    private LocalDateTime creation_time;

    private String city;

    private String country;

    private String streetAddress;

    private List<OrderDetailDto> orderDetailDtos;

    public ShopOrderDto(LocalDateTime creation_time, String city, String country, String streetAddress, List<OrderDetailDto> orderDetailDtos) {
        this.creation_time = creation_time;
        this.city = city;
        this.country = country;
        this.streetAddress = streetAddress;
        this.orderDetailDtos = orderDetailDtos;
    }
}

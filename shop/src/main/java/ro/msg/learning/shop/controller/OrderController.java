package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.ShopOrderDto;
import ro.msg.learning.shop.entity.ShopOrder;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.service.ShopOrderService;


@RestController
public class OrderController {

    @Autowired
    private ShopOrderService shopOrderService;

    @Autowired
    private LocationRepository locationRepository;

    @PostMapping(path = "/order")
    public ShopOrder placeOrder(@RequestBody ShopOrderDto shopOrderDto) {
        ShopOrder order = shopOrderService.createOrder(shopOrderDto);
        return order;
    }
}

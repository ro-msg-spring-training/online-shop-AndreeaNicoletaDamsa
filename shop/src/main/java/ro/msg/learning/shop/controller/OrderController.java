package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        return shopOrderService.createOrder(shopOrderDto);
    }

    @GetMapping(path = "/order/{id}")
    public ResponseEntity<ShopOrder> getOrderWithId(@PathVariable Integer id) {
        ShopOrder shopOrder = null;
        try {
            shopOrder = shopOrderService.findById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(shopOrder, HttpStatus.OK);
    }
}

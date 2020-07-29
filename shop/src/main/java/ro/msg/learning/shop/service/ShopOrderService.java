package ro.msg.learning.shop.service;

import ro.msg.learning.shop.dto.SelectedProductDto;
import ro.msg.learning.shop.dto.ShopOrderDto;
import ro.msg.learning.shop.entity.ShopOrder;

import java.util.List;

public interface ShopOrderService {

    ShopOrder createOrder(ShopOrderDto shopOrderDto);
    ShopOrder findById(Integer id);

}

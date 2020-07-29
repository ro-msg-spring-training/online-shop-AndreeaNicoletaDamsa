package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.dto.SelectedProductDto;
import ro.msg.learning.shop.dto.ShopOrderDto;

import java.util.List;

public interface LocationStrategy {
    List<SelectedProductDto> select(ShopOrderDto shopOrderDto) throws Exception;

}

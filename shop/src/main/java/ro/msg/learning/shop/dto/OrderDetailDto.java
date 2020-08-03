package ro.msg.learning.shop.dto;

import lombok.Data;

@Data
public class OrderDetailDto {
    private Integer id;
    private Integer quantity;

    public OrderDetailDto(Integer id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}

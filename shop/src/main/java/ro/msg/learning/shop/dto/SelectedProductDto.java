package ro.msg.learning.shop.dto;

import lombok.Data;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;

@Data
public class SelectedProductDto {
    private Product product;
    private Location location;
    private Integer quantity;

    public SelectedProductDto(Product product, Location location, Integer quantity) {
        this.product = product;
        this.location = location;
        this.quantity = quantity;
    }
}

package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="stock")
@NoArgsConstructor
public class Stock extends GeneralEntity {

    @ManyToOne
    @JoinColumn(name="product")
    private Product product;
    @ManyToOne
    @JoinColumn(name="location")
    private Location location;

    private Integer quantity;

    public Stock(Product product, Location location, Integer quantity) {
        this.product = product;
        this.location = location;
        this.quantity = quantity;
    }
}

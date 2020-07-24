package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="stock")
public class Stock extends GeneralEntity {

    @ManyToOne(targetEntity = Stock.class)
    @JoinColumn(name="product")
    private Product product;
    @ManyToOne(targetEntity = Stock.class)
    @JoinColumn(name="location")
    private Location location;

    private Integer quantity;

}

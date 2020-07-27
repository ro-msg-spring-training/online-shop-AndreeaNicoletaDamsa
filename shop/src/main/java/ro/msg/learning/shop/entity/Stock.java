package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="stock")
public class Stock extends GeneralEntity {

    @ManyToOne
    @JoinColumn(name="product")
    private Product product;
    @ManyToOne
    @JoinColumn(name="location")
    private Location location;

    private Integer quantity;

}

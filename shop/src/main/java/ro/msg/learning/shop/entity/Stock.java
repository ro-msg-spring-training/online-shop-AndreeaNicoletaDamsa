package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;
    @ManyToOne(targetEntity = Stock.class)
    @JoinColumn(name="id")
    private Product product;
    @ManyToOne(targetEntity = Stock.class)
    @JoinColumn(name="id")
    private Location location;

}

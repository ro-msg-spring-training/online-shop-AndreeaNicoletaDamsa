package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="product")
public class Product  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Float price;
    @Column
    private Double weight;
    @ManyToOne(targetEntity = ProductCategory.class)
    @JoinColumn(name="id")
    private ProductCategory product_category;
    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name="id")
    private Supplier supplier;
    @Column
    private String ImageURL;
    @OneToMany(mappedBy = "product")
    private List<Stock> stocks;
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;
}

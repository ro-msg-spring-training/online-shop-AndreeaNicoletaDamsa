package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="product")
public class Product  extends GeneralEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column
//    private Integer id;

    public Product(String name, String description, Float price, Double weight, ProductCategory productCategory, Supplier supplier, String imageURL) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.productCategory = productCategory;
        this.supplier = supplier;
        this.imageURL = imageURL;
    }

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Float price;
    @Column
    private Double weight;
    @ManyToOne(targetEntity = ProductCategory.class)
    @JoinColumn(name="category")
    private ProductCategory productCategory;
    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name="supplier")
    private Supplier supplier;
    @Column(name = "image_url")
    private String imageURL;
    @OneToMany(mappedBy = "product")
    private List<Stock> stocks;
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    public Product(){};
}

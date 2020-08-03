package ro.msg.learning.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product extends GeneralEntity {

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
    @ManyToOne
    @JoinColumn(name = "category")
    private ProductCategory productCategory;
    @ManyToOne
    @JoinColumn(name = "supplier")
    private Supplier supplier;
    @Column(name = "image_url")
    private String imageURL;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Stock> stocks;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderDetail> orderDetails;

}

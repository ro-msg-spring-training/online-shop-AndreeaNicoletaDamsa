package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="product")
@NoArgsConstructor
public class Product  extends GeneralEntity {

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
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    @Transient
    private List<Stock> stocks;
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    @Transient
    private List<OrderDetail> orderDetails;

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", productCategory=" + productCategory +
                ", supplier=" + supplier +
                ", imageURL='" + imageURL + '\'' +
                ", stocks=" + stocks +
                ", orderDetails=" + orderDetails +
                '}';
    }

}

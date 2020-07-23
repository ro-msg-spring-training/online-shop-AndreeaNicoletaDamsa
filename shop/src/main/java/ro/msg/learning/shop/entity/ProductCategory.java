package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "product_category")
public class ProductCategory extends GeneralEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column
//    private Integer id;

    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Column
    private String name;
    @Column
    private String description;
    @OneToMany(mappedBy = "productCategory", cascade= CascadeType.ALL)
    private List<Product> products;
}

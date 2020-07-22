package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "product_category")
public class ProductCategory{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;
    @OneToMany(mappedBy = "product_category", cascade= CascadeType.ALL)
    private List<Product> products;
}

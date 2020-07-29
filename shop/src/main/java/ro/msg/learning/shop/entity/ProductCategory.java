package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "product_category")
@NoArgsConstructor
public class ProductCategory extends GeneralEntity {

    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Column
    private String name;
    @Column
    private String description;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "productCategory", cascade= CascadeType.ALL)
    @Transient
    private List<Product> products;

}

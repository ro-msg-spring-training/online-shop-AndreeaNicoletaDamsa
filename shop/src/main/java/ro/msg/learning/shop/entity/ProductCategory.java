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
@Table(name = "product_category")
@NoArgsConstructor
public class ProductCategory extends GeneralEntity {

    @Column
    private String name;
    @Column
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;

}

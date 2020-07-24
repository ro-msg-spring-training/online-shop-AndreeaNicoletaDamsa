package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "supplier")
public class Supplier extends GeneralEntity {


    public Supplier(String name) {
        this.name = name;
    }

    @Column
    private String name;
    @OneToMany(mappedBy = "supplier",fetch = FetchType.LAZY)
    @Transient
    private List<Product> products;

    public Supplier(){}



}

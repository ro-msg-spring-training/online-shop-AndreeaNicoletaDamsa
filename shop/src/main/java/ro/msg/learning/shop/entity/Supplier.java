package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "supplier")
public class Supplier extends GeneralEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column
//    private Integer id;

    public Supplier(String name) {
        this.name = name;
    }

    @Column
    private String name;
    @OneToMany(mappedBy = "supplier")
    private List<Product> products;



}

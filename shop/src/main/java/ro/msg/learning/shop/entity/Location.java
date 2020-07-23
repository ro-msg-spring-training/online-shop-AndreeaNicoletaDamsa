package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="location")
public class Location extends GeneralEntity   {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column
//    private Integer id;
    @Column
    private String name;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "location")
    private List<Stock> stocks;
    @OneToMany(mappedBy = "location")
    private List<ShopOrder> shopOrders;
    @OneToMany(mappedBy = "location")
    private List<Revenue> revenues;
}

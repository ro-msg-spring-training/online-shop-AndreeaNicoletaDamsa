package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name="shop_order")
public class ShopOrder  extends GeneralEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column
//    private Integer id;
    @ManyToOne(targetEntity = Location.class)
    @JoinColumn(name="location")
    private Location location;
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name="customer")
    private Customer customer;
    @Column
    private LocalDateTime creation_time;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "shopOrder")
    private List<OrderDetail> orderDetails;




}

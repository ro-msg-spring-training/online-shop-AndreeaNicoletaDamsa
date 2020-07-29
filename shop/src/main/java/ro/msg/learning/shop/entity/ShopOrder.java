package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="shop_order")
@NoArgsConstructor
public class ShopOrder  extends GeneralEntity {

    @ManyToOne
    @JoinColumn(name="shipped_from")
    private Location location;
    @ManyToOne
    @JoinColumn(name="customer")
    private Customer customer;
    @Column
    private LocalDateTime creation_time;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "shopOrder",fetch = FetchType.LAZY)
    @Transient
    private List<OrderDetail> orderDetails;

    public ShopOrder(Location location, Customer customer, LocalDateTime creation_time, Address address) {
        this.location = location;
        this.customer = customer;
        this.creation_time = creation_time;
        this.address = address;
        orderDetails = new ArrayList<>();
    }
}

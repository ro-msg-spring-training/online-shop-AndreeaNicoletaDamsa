package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "shop_order")
@NoArgsConstructor
public class ShopOrder extends GeneralEntity {

    @ManyToOne
    @JoinColumn(name = "shipped_from")
    private Location location;
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
    @Column
    private LocalDateTime creationTime;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "shopOrder", fetch = FetchType.LAZY)
    @Transient
    private List<OrderDetail> orderDetails;

    public ShopOrder(Location location, Customer customer, LocalDateTime creationTime, Address address) {
        this.location = location;
        this.customer = customer;
        this.creationTime = creationTime;
        this.address = address;
    }

    @Override
    public String toString() {
        return "ShopOrder{" +
                "location=" + location +
                ", customer=" + customer +
                ", creationTime=" + creationTime +
                ", address=" + address +
                '}';
    }
}

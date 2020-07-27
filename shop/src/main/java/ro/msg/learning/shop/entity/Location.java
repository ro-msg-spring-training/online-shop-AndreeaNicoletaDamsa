package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="location")
public class Location extends GeneralEntity   {

    @Column
    private String name;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "location",fetch = FetchType.EAGER)
    private List<Stock> stocks;
    @OneToMany(mappedBy = "location",fetch = FetchType.LAZY)
    private List<ShopOrder> shopOrders;
    @OneToMany(mappedBy = "location",fetch = FetchType.LAZY)
    private List<Revenue> revenues;

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", address=" + address;
    }
}

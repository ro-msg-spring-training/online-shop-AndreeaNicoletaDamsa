package ro.msg.learning.shop.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name="customer")
public class Customer extends GeneralEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column
//    private Integer id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String emailAddress;

    @OneToMany(mappedBy = "customer")
    private List<ShopOrder> shopOrders;

    public Customer(String firstName, String lastName, String username, String password, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Person [firstName=" + this.firstName + ", lastName=" + this.lastName
                + "]";
    }
}

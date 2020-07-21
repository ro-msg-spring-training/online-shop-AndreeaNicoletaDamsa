package ro.msg.learning.shop.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue
    private Integer id;


    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String emailAddress;

    public String getFirstName() {
        return this.firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return this.lastName;
    }


    public void setLastName(String lastname) {
        this.lastName = lastname;
    }


    @Override
    public String toString() {
        return "Person [firstName=" + this.firstName + ", lastName=" + this.lastName
                + "]";
    }
}

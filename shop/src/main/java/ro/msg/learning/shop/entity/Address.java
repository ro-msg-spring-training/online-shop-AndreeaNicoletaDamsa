package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Data
@Embeddable
public class Address {
    @Column
    private String city;
    @Column
    private String country;
    @Column(name="street_address")
    private String streetAddress;
}

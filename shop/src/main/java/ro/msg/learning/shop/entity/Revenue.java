package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name="revenue")
@Entity
public class Revenue extends GeneralEntity {

    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;
    @Column
    private LocalDate date_of;
    @Column
    private Float summation;
}

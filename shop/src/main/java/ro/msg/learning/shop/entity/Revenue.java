package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name="revenue")
@Entity
public class Revenue{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;
    @ManyToOne(targetEntity = Location.class)
    @JoinColumn(name = "id")
    private Location location;
    @Column
    private LocalDate date_of;
    @Column
    private Float summation;
}

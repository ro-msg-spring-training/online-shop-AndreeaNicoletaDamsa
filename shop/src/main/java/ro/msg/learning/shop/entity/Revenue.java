package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "revenue")
@Entity
@NoArgsConstructor
public class Revenue extends GeneralEntity {

    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;
    @Column
    private LocalDate dateOf;
    @Column
    private Float summation;
}

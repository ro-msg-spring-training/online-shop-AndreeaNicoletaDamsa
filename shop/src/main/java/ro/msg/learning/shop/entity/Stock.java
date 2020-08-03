package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="stock")
@NoArgsConstructor
@AllArgsConstructor
public class Stock extends GeneralEntity {

    @ManyToOne
    @JoinColumn(name="product")
    private Product product;
    @ManyToOne
    @JoinColumn(name="location")
    private Location location;

    private Integer quantity;

}

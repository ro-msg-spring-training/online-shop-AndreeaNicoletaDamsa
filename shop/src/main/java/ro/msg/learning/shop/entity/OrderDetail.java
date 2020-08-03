package ro.msg.learning.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "order_detail")
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail extends GeneralEntity  {

        @ManyToOne
        @JoinColumn(name="shop_order")
        @JsonIgnore
        private ShopOrder shopOrder;
        @ManyToOne
        @JoinColumn(name="product")
        private Product product;
        @Column
        private Integer quantity;

}

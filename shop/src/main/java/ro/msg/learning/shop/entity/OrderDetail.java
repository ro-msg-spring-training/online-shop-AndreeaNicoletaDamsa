package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_detail")
public class OrderDetail extends GeneralEntity  {
//        @Id
//        @GeneratedValue(strategy = GenerationType.AUTO)
//        @Column
//        private Integer id;
        @ManyToOne(targetEntity = ShopOrder.class)
        @JoinColumn(name="shop_order")
        private ShopOrder shopOrder;
        @ManyToOne(targetEntity = Product.class)
        @JoinColumn(name="product")
        private Product product;
        @Column
        private Integer quantity;
}

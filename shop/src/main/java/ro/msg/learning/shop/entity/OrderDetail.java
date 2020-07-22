package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_detail")
public class OrderDetail  {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column
        private Integer id;
        @ManyToOne(targetEntity = ShopOrder.class)
        @JoinColumn(name="id")
        private ShopOrder shop_order;
        @ManyToOne(targetEntity = Product.class)
        @JoinColumn(name="id")
        private Product product;
        @Column
        private Integer quantity;
}

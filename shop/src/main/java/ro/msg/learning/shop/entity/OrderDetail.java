package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_detail")
public class OrderDetail extends GeneralEntity  {

        @ManyToOne(targetEntity = ShopOrder.class)
        @JoinColumn(name="shop_order")
        private ShopOrder shopOrder;
        @ManyToOne(targetEntity = Product.class)
        @JoinColumn(name="product")
        private Product product;
        @Column
        private Integer quantity;

        public OrderDetail(ShopOrder shopOrder, Product product, Integer quantity) {
                this.shopOrder = shopOrder;
                this.product = product;
                this.quantity = quantity;
        }
}

package ro.msg.learning.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_detail")
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

        public OrderDetail(ShopOrder shopOrder, Product product, Integer quantity) {
                this.shopOrder = shopOrder;
                this.product = product;
                this.quantity = quantity;
        }
}

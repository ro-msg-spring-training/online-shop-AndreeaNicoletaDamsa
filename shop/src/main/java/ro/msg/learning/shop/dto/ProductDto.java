package ro.msg.learning.shop.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Integer productId;
    private String productName;
    private String productDescription;
    private Float price;
    private Double weight;
    private Integer categoryId;
    private String categoryDescription;
    private String categoryName;
    private Integer supplierId;
    private String supplierName;
    private String imageURL;
}

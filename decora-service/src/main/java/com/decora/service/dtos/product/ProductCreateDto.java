package com.decora.service.dtos.product;

import com.decora.service.models.enums.DiscountRateEnum;
import com.decora.service.models.enums.ProductStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductCreateDto {
    private String title;
    private String description;
    private String size;
    private Integer collection;
    private Long categoryID;
    private List<Long> colorIDS;
    private Double price;
    private DiscountRateEnum discountRate;
    private ProductStatus status;
}

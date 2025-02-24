package com.decora.service.dtos.product;

import com.decora.service.base.BaseIdDto;
import com.decora.service.dtos.product_category.ProductCategoryListDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductListDto extends BaseIdDto {
    private String title;
    private ProductCategoryListDto category;
    private Double price;
    private Integer discountRate;
    private String status;
    private String imageUrl;
}

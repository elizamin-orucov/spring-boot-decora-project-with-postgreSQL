package com.decora.service.dtos.product;

import com.decora.service.base.BaseIdDto;
import com.decora.service.dtos.product_category.ProductCategoryListDto;
import com.decora.service.dtos.product_color.ProductColorListDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductDto extends BaseIdDto {
    private String title;
    private String description;
    private ProductCategoryListDto category;
    private String slug;
    private Double price;
    private String size;
    private Integer collection;
    private ProductColorListDto color;
    private String discountRate;
    private String status;
    private List<ProductImageListDto> images;
}

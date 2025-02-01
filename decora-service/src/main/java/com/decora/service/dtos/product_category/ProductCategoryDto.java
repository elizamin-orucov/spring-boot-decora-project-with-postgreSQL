package com.decora.service.dtos.product_category;

import com.decora.service.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductCategoryDto extends BaseDto {
    private String categoryName;
}

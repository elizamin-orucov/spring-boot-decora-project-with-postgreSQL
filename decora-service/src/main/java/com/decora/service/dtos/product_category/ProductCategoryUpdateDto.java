package com.decora.service.dtos.product_category;


import com.decora.service.base.BaseDto;
import com.decora.service.base.BaseIdDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductCategoryUpdateDto extends BaseIdDto {
    private String categoryName;
}

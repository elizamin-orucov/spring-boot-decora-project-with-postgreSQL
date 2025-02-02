package com.decora.service.dtos.product_color;

import com.decora.service.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductColorDto extends BaseDto {
    private String colorName;
    private String colorCode;
}

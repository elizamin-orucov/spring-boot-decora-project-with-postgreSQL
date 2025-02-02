package com.decora.service.dtos.product_color;

import com.decora.service.base.BaseIdDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductColorUpdateDto extends BaseIdDto {
    private String colorName;
    private String colorCode;
}

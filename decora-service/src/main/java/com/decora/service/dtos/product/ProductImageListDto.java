package com.decora.service.dtos.product;

import com.decora.service.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductImageListDto extends BaseDto {
    private String imageUrl;
}

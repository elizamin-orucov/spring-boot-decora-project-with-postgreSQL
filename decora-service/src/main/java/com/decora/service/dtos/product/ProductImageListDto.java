package com.decora.service.dtos.product;

import com.decora.service.base.BaseIdDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductImageListDto extends BaseIdDto {
    private String imageUrl;
}

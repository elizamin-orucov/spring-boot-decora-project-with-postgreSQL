package com.decora.service.dtos.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductUpdateDto extends ProductCreateDto {
    private Long id;
}

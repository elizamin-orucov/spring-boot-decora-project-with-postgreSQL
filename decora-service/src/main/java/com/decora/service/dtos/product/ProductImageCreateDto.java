package com.decora.service.dtos.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductImageCreateDto {
    private Long productId;
    private String imagePath;
}

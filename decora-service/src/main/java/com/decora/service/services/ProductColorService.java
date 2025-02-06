package com.decora.service.services;

import com.decora.service.dtos.product_color.ProductColorCreateDto;
import com.decora.service.dtos.product_color.ProductColorDto;
import com.decora.service.dtos.product_color.ProductColorListDto;
import com.decora.service.dtos.product_color.ProductColorUpdateDto;

public interface ProductColorService extends BaseCRUDService<
        ProductColorDto,
        ProductColorListDto,
        ProductColorCreateDto,
        ProductColorUpdateDto
        > {
}

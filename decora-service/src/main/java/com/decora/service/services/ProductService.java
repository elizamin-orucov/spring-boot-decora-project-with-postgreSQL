package com.decora.service.services;

import com.decora.service.dtos.product.ProductCreateDto;
import com.decora.service.dtos.product.ProductDto;
import com.decora.service.dtos.product.ProductListDto;
import com.decora.service.dtos.product.ProductUpdateDto;

public interface ProductService extends BaseCRUDService<
        ProductDto,
        ProductListDto,
        ProductCreateDto,
        ProductUpdateDto
        >{
    ProductDto detail(String slug);
}

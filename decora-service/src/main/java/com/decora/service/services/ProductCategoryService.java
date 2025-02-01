package com.decora.service.services;

import com.decora.service.dtos.product_category.ProductCategoryCreateDto;
import com.decora.service.dtos.product_category.ProductCategoryDto;
import com.decora.service.dtos.product_category.ProductCategoryListDto;
import com.decora.service.dtos.product_category.ProductCategoryUpdateDto;

public interface ProductCategoryService extends BaseCRUDService<
        ProductCategoryDto,
        ProductCategoryListDto,
        ProductCategoryCreateDto,
        ProductCategoryUpdateDto>{
}

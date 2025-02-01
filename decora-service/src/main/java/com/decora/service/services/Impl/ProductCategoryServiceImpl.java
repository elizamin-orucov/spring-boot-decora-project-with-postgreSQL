package com.decora.service.services.Impl;

import com.decora.service.dtos.product_category.ProductCategoryCreateDto;
import com.decora.service.dtos.product_category.ProductCategoryDto;
import com.decora.service.dtos.product_category.ProductCategoryListDto;
import com.decora.service.dtos.product_category.ProductCategoryUpdateDto;
import com.decora.service.repositories.attributes.ProductCategoryRepository;
import com.decora.service.services.ProductCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository categoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<ProductCategoryListDto> list(Pageable pageable) {
        return null;
    }

    @Override
    public ProductCategoryDto detail(Long id) {
        return null;
    }

    @Override
    public String create(ProductCategoryCreateDto productCategoryCreateDto) {
        return null;
    }

    @Override
    public String update(ProductCategoryUpdateDto productCategoryUpdateDto) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }
}

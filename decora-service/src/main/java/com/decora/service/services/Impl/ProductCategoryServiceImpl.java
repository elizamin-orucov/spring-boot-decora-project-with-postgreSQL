package com.decora.service.services.Impl;

import com.decora.service.dtos.product_category.ProductCategoryCreateDto;
import com.decora.service.dtos.product_category.ProductCategoryDto;
import com.decora.service.dtos.product_category.ProductCategoryListDto;
import com.decora.service.dtos.product_category.ProductCategoryUpdateDto;
import com.decora.service.dtos.response.ApiResponseDto;
import com.decora.service.mappers.attributes.ProductCategoryMapper;
import com.decora.service.models.attributes.ProductCategoryEntity;
import com.decora.service.repositories.attributes.ProductCategoryRepository;
import com.decora.service.services.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository categoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<ProductCategoryListDto> list(Pageable pageable) {
        log.info("Fetching product categories with pagination: {}", pageable);
        Page<ProductCategoryEntity> categoryEntities = categoryRepository.findAll(pageable);
        return categoryEntities.map(ProductCategoryMapper.INSTANCE::toListDto);
    }

    @Override
    public ProductCategoryDto detail(Long id) {
        log.info("Fetching product category details for id: {}", id);
        ProductCategoryEntity entity = getProductCategoryEntity(id);
        return ProductCategoryMapper.INSTANCE.toDto(entity);
    }

    @Override
    public ApiResponseDto<ProductCategoryDto> create(ProductCategoryCreateDto productCategoryCreateDto) {
        log.info("Creating new product category: {}", productCategoryCreateDto);
        ProductCategoryEntity entity = ProductCategoryMapper.INSTANCE.toEntity(productCategoryCreateDto);
        ProductCategoryEntity savedEntity = categoryRepository.save(entity);
        log.info("Product category created successfully with id: {}", savedEntity.getId());

        ProductCategoryDto responseDto = ProductCategoryMapper.INSTANCE.toDto(savedEntity);
        return ApiResponseDto.<ProductCategoryDto>builder()
                .success(true)
                .message("category added successfully")
                .response(responseDto)
                .build();
    }

    @Override
    public ApiResponseDto<ProductCategoryUpdateDto> update(ProductCategoryUpdateDto productCategoryUpdateDto) {
        log.info("Updating product category with id: {}", productCategoryUpdateDto.getId());
        ProductCategoryEntity entity = ProductCategoryMapper.INSTANCE.toEntity(productCategoryUpdateDto);
        categoryRepository.save(entity);
        log.info("Product category successfully updated: {}", productCategoryUpdateDto.getId());
        return ApiResponseDto.<ProductCategoryUpdateDto>builder()
                .success(true)
                .message("category successfully renewed")
                .response(productCategoryUpdateDto)
                .build();
    }

    @Override
    public ApiResponseDto<ProductCategoryDto> delete(Long id) {
        log.info("Deleting product category with id: {}", id);
        ProductCategoryEntity entity = getProductCategoryEntity(id);
        if (entity == null) {
            log.warn("Product category with id {} not found", id);
            return ApiResponseDto.<ProductCategoryDto>builder()
                    .success(false)
                    .message("category not found")
                    .build();
        }
        categoryRepository.delete(entity);
        log.info("Product category with id {} deleted successfully", id);
        ProductCategoryDto dto = ProductCategoryMapper.INSTANCE.toDto(entity);
        return ApiResponseDto.<ProductCategoryDto>builder()
                .success(true)
                .message(String.format("category with id %d deleted", id))
                .response(dto)
                .build();
    }

    private ProductCategoryEntity getProductCategoryEntity(Long id) {
        log.debug("Fetching product category entity for id: {}", id);
        Optional<ProductCategoryEntity> entityOptional = categoryRepository.findById(id);
        if (entityOptional.isEmpty()) {
            log.warn("Product category entity not found for id: {}", id);
        }
        return entityOptional.orElse(null);
    }
}
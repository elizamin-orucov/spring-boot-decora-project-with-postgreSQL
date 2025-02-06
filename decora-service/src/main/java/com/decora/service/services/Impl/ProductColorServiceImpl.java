package com.decora.service.services.Impl;

import com.decora.service.dtos.product_color.ProductColorCreateDto;
import com.decora.service.dtos.product_color.ProductColorDto;
import com.decora.service.dtos.product_color.ProductColorListDto;
import com.decora.service.dtos.product_color.ProductColorUpdateDto;
import com.decora.service.dtos.response.ApiResponseDto;
import com.decora.service.mappers.attributes.ProductColorMapper;
import com.decora.service.models.attributes.ProductColorEntity;
import com.decora.service.repositories.attributes.ProductColorRepository;
import com.decora.service.services.ProductColorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ProductColorServiceImpl implements ProductColorService {

    private final ProductColorRepository colorRepository;

    public ProductColorServiceImpl(ProductColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    public Page<ProductColorListDto> list(Pageable pageable) {
        log.info("Fetching product colors with pagination: {}", pageable);
        Page<ProductColorEntity> colorEntities = colorRepository.findAll(pageable);
        return colorEntities.map(ProductColorMapper.INSTANCE::toListDto);
    }

    @Override
    public ProductColorDto detail(Long id) {
        log.info("Fetching product color details for id: {}", id);
        ProductColorEntity entity = getProductColorEntity(id);
        return ProductColorMapper.INSTANCE.toDto(entity);
    }

    @Override
    public ApiResponseDto<ProductColorDto> create(ProductColorCreateDto productColorCreateDto) {
        log.info("Creating new product color: {}", productColorCreateDto);
        ProductColorEntity entity = ProductColorMapper.INSTANCE.toEntity(productColorCreateDto);
        ProductColorEntity savedEntity = colorRepository.save(entity);
        log.info("Product color created successfully with id: {}", savedEntity.getId());

        ProductColorDto responseDto = ProductColorMapper.INSTANCE.toDto(savedEntity);
        return ApiResponseDto.<ProductColorDto>builder()
                .success(true)
                .message("product color added successfully")
                .response(responseDto)
                .build();
    }

    @Override
    public ApiResponseDto<ProductColorUpdateDto> update(ProductColorUpdateDto productColorUpdateDto) {
        log.info("Updating product color with id: {}", productColorUpdateDto.getId());
        ProductColorEntity entity = ProductColorMapper.INSTANCE.toEntity(productColorUpdateDto);
        colorRepository.save(entity);
        log.info("Product color successfully updated: {}", productColorUpdateDto.getId());
        return ApiResponseDto.<ProductColorUpdateDto>builder()
                .success(true)
                .message("product color successfully renewed")
                .response(productColorUpdateDto)
                .build();
    }

    @Override
    public ApiResponseDto<ProductColorDto> delete(Long id) {
        log.info("Deleting product color with id: {}", id);
        ProductColorEntity colorEntity = getProductColorEntity(id);
        if (colorEntity == null){
            log.warn("Product color with id {} not found", id);
            return ApiResponseDto.<ProductColorDto>builder()
                    .success(false)
                    .message("color not found")
                    .build();
        }

        colorRepository.delete(colorEntity);
        log.info("Product color with id {} deleted successfully", id);
        ProductColorDto colorDto = ProductColorMapper.INSTANCE.toDto(colorEntity);
        return ApiResponseDto.<ProductColorDto>builder()
                .success(true)
                .message(String.format("color with id %d deleted", id))
                .response(colorDto)
                .build();
    }

    private ProductColorEntity getProductColorEntity(Long id) {
        log.debug("Fetching product color entity for id: {}", id);
        Optional<ProductColorEntity> entityOptional = colorRepository.findById(id);
        if (entityOptional.isEmpty()) {
            log.warn("Product color entity not found for id: {}", id);
        }
        return entityOptional.orElse(null);
    }
}

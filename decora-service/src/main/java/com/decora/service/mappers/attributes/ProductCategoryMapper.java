package com.decora.service.mappers.attributes;

import com.decora.service.dtos.product_category.ProductCategoryCreateDto;
import com.decora.service.dtos.product_category.ProductCategoryDto;
import com.decora.service.dtos.product_category.ProductCategoryListDto;
import com.decora.service.dtos.product_category.ProductCategoryUpdateDto;
import com.decora.service.models.attributes.ProductCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProductCategoryMapper {
    ProductCategoryMapper INSTANCE = Mappers.getMapper(ProductCategoryMapper.class);

    // to Dto
    ProductCategoryListDto toListDto(ProductCategoryEntity entity);

    ProductCategoryDto toDto(ProductCategoryEntity entity);

    // to Entity
    @Mapping(source = "categoryName", target = "categoryName")
    ProductCategoryEntity toEntity(ProductCategoryCreateDto dto);

    ProductCategoryEntity toEntity(ProductCategoryUpdateDto dto);
}


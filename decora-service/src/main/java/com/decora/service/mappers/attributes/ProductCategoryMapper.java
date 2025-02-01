package com.decora.service.mappers.attributes;

import com.decora.service.dtos.product_category.ProductCategoryCreateDto;
import com.decora.service.dtos.product_category.ProductCategoryDto;
import com.decora.service.dtos.product_category.ProductCategoryListDto;
import com.decora.service.dtos.product_category.ProductCategoryUpdateDto;
import com.decora.service.models.attributes.ProductCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductCategoryMapper {
    ProductCategoryMapper INSTANCE = Mappers.getMapper(ProductCategoryMapper.class);

    // to Dto
//    @Mapping(target = "categoryName", source = "categoryName")
    ProductCategoryListDto toListDto(ProductCategoryEntity entity);
    ProductCategoryDto toDto(ProductCategoryEntity entity);

    // to Entity
//    @Mapping(target = "categoryName", source = "categoryName")
    ProductCategoryEntity toEntity(ProductCategoryCreateDto dto);

    ProductCategoryEntity toEntity(ProductCategoryUpdateDto dto);
}


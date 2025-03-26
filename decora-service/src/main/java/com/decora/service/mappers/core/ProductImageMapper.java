package com.decora.service.mappers.core;

import com.decora.service.dtos.product.ProductImageListDto;
import com.decora.service.models.core.product.ProductImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProductImageMapper {
    ProductImageMapper INSTANCE = Mappers.getMapper(ProductImageMapper.class);

    ProductImageListDto toListDto(ProductImageEntity entity);
}

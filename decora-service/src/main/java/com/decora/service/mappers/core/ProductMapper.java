package com.decora.service.mappers.core;

import com.decora.service.dtos.product.ProductCreateDto;
import com.decora.service.dtos.product.ProductDto;
import com.decora.service.dtos.product.ProductListDto;
import com.decora.service.models.core.product.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // to Dto
    ProductListDto toListDto(ProductEntity entity);

    ProductDto toDto(ProductEntity entity);

    // to Entity
    ProductEntity toEntity(ProductCreateDto dto);

}

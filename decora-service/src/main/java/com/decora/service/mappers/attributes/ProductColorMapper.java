package com.decora.service.mappers.attributes;

import com.decora.service.dtos.product_color.ProductColorCreateDto;
import com.decora.service.dtos.product_color.ProductColorDto;
import com.decora.service.dtos.product_color.ProductColorListDto;
import com.decora.service.dtos.product_color.ProductColorUpdateDto;
import com.decora.service.models.attributes.ProductColorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProductColorMapper {
    ProductColorMapper INSTANCE = Mappers.getMapper(ProductColorMapper.class);

    // to Dto
    ProductColorListDto toListDto(ProductColorEntity entity);
    ProductColorDto toDto(ProductColorEntity entity);

    // to Entity
//    @Mapping(source = "colorName", target = "colorName")
//    @Mapping(source = "colorCode", target = "colorCode")
    ProductColorEntity toEntity(ProductColorCreateDto dto);

    ProductColorEntity toEntity(ProductColorUpdateDto dto);
}


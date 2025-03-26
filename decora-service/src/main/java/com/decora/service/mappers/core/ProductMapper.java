package com.decora.service.mappers.core;

import com.decora.service.dtos.product.*;
import com.decora.service.models.core.product.ProductEntity;
import com.decora.service.models.core.product.ProductImageEntity;
import com.decora.service.models.enums.DiscountRateEnum;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // to Dto
    @Mapping(source = "images", target = "image", qualifiedByName = "mapFirstImage")
    ProductListDto toListDto(ProductEntity entity);

    ProductDto toDto(ProductEntity entity);

    // for create
    ProductEntity toEntity(ProductCreateDto dto);

    // for update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ProductUpdateDto dto, @MappingTarget ProductEntity entity);

    @Named("mapFirstImage")
    default ProductImageListDto mapFirstImageUrl(List<ProductImageEntity> images) {
        if (images != null && !images.isEmpty()) {
            ProductImageListDto imageListDto = new ProductImageListDto();
            imageListDto.setId(images.get(0).getId());
            imageListDto.setImageUrl(images.get(0).getImageUrl());
            return imageListDto;
        }
        return null;
    }
}



package com.decora.service.mappers.core;

import com.decora.service.dtos.product.ProductCreateDto;
import com.decora.service.dtos.product.ProductDto;
import com.decora.service.dtos.product.ProductListDto;
import com.decora.service.dtos.product.ProductUpdateDto;
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
    @Mapping(source = "images", target = "imageUrl", qualifiedByName = "mapFirstImageUrl")
    ProductListDto toListDto(ProductEntity entity);

    ProductDto toDto(ProductEntity entity);

    // Create üçün istifadə et
    ProductEntity toEntity(ProductCreateDto dto);

    // Update üçün mövcud entity-ə map et
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ProductUpdateDto dto, @MappingTarget ProductEntity entity);

    @Named("mapFirstImageUrl")
    default String mapFirstImageUrl(List<ProductImageEntity> images) {
        if (images != null && !images.isEmpty()) {
            return images.get(0).getImageUrl();
        }
        return null;
    }
}



package com.decora.service.mappers.core;

import com.decora.service.dtos.product.ProductCreateDto;
import com.decora.service.dtos.product.ProductDto;
import com.decora.service.dtos.product.ProductListDto;
import com.decora.service.models.core.product.ProductEntity;
import com.decora.service.models.enums.DiscountRateEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // to Dto
    ProductListDto toListDto(ProductEntity entity);

    ProductDto toDto(ProductEntity entity);

    // to Entity
//    @Mapping(source = "description", target = "description")
    ProductEntity toEntity(ProductCreateDto dto);

    // Enum -> Integer dönüşümü
//    @Named("mapDiscountRateEnumToInteger")
//    default Integer mapDiscountRateEnumToInteger(DiscountRateEnum discountRate) {
//        return (discountRate != null) ? discountRate.ordinal() : null;
//    }

}

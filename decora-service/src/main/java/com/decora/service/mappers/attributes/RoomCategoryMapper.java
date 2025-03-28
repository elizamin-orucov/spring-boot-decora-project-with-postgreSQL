package com.decora.service.mappers.attributes;

import com.decora.service.dtos.room_category.RoomCategoryCreateDto;
import com.decora.service.dtos.room_category.RoomCategoryDto;
import com.decora.service.dtos.room_category.RoomCategoryListDto;
import com.decora.service.dtos.room_category.RoomCategoryUpdateDto;
import com.decora.service.models.attributes.RoomCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface RoomCategoryMapper {
    RoomCategoryMapper INSTANCE = Mappers.getMapper(RoomCategoryMapper.class);

    // to dto
    RoomCategoryListDto toListDto(RoomCategoryEntity entity);

    RoomCategoryDto toDto(RoomCategoryEntity entity);

    // to entity
    RoomCategoryEntity toEntity(RoomCategoryCreateDto createDto);

    RoomCategoryEntity toEntity(RoomCategoryUpdateDto updateDto);

}

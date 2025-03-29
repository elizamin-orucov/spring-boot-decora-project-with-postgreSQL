package com.decora.service.mappers.core;

import com.decora.service.dtos.room.*;
import com.decora.service.models.core.rooms.RoomEntity;
import com.decora.service.models.core.rooms.RoomImageEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    // to Dto
    @Mapping(source = "images", target = "image", qualifiedByName = "mapFirstRoomImage")
    RoomListDto toListDto(RoomEntity entity);

    RoomDto toDto(RoomEntity entity);

    // to Entity
    RoomEntity toEntity(RoomCreateDto dto);

    RoomEntity toEntity(RoomUpdateDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(RoomUpdateDto dto, @MappingTarget RoomEntity entity);

    @Named("mapFirstRoomImage")
    default RoomImageListDto mapFirstRoomImageUrl(List<RoomImageEntity> roomImages) {
        if (roomImages != null && !roomImages.isEmpty()) {
            RoomImageListDto imageListDto = new RoomImageListDto();
            imageListDto.setId(roomImages.get(0).getId());
            imageListDto.setImageUrl(roomImages.get(0).getImageUrl());
            return imageListDto;
        }
        return null;
    }
}

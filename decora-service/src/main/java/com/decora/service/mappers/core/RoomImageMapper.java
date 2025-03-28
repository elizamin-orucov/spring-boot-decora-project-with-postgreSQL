package com.decora.service.mappers.core;

import com.decora.service.dtos.room.RoomImageListDto;
import com.decora.service.models.core.rooms.RoomImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface RoomImageMapper {
    RoomImageMapper INSTANCE = Mappers.getMapper(RoomImageMapper.class);

    RoomImageListDto toListDto(RoomImageEntity entity);
}

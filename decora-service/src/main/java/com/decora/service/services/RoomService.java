package com.decora.service.services;

import com.decora.service.dtos.room.RoomCreateDto;
import com.decora.service.dtos.room.RoomDto;
import com.decora.service.dtos.room.RoomListDto;
import com.decora.service.dtos.room.RoomUpdateDto;

public interface RoomService extends BaseCRUDService<
        RoomDto,
        RoomListDto,
        RoomCreateDto,
        RoomUpdateDto
        > {
    RoomDto detail(String slug);
}

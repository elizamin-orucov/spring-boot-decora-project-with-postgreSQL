package com.decora.service.services;

import com.decora.service.dtos.room_category.RoomCategoryCreateDto;
import com.decora.service.dtos.room_category.RoomCategoryDto;
import com.decora.service.dtos.room_category.RoomCategoryListDto;
import com.decora.service.dtos.room_category.RoomCategoryUpdateDto;

public interface RoomCategoryService extends BaseCRUDService<
        RoomCategoryDto,
        RoomCategoryListDto,
        RoomCategoryCreateDto,
        RoomCategoryUpdateDto
        >{
}

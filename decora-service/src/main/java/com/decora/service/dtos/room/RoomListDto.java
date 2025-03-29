package com.decora.service.dtos.room;

import com.decora.service.base.BaseIdDto;
import com.decora.service.dtos.room_category.RoomCategoryListDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomListDto extends BaseIdDto {
    private String title;
    private String slug;
    private RoomCategoryListDto category;
    private RoomImageListDto image;
}

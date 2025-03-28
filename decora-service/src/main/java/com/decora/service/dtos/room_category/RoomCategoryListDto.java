package com.decora.service.dtos.room_category;

import com.decora.service.base.BaseIdDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomCategoryListDto extends BaseIdDto {
    private String categoryName;
}

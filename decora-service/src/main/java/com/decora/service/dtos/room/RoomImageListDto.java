package com.decora.service.dtos.room;

import com.decora.service.base.BaseIdDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomImageListDto extends BaseIdDto {
    private String imageUrl;
}

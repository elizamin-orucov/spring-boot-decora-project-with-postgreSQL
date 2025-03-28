package com.decora.service.dtos.room;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomImageCreateDto {
    private Long roomID;
    private String imagePath;
}

package com.decora.service.dtos.room;

import com.decora.service.base.BaseIdDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RoomUpdateDto extends BaseIdDto {
    private String title;
    private String description;
    private Long categoryID;
    private List<Long> productIDS;
}

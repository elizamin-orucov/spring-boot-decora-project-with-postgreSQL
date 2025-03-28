package com.decora.service.dtos.room;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RoomCreateDto {
    private String title;
    private String description;
    private Long categoryID;
    private List<Long> productIDS;
}


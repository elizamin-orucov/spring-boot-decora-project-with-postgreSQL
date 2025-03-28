package com.decora.service.dtos.room;

import com.decora.service.base.BaseIdDto;
import com.decora.service.dtos.product.ProductListDto;
import com.decora.service.dtos.room_category.RoomCategoryListDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RoomDto extends BaseIdDto {
    private String title;
    private String description;
    private RoomCategoryListDto category;
    private List<ProductListDto> products;
    private List<RoomImageListDto> images;
}

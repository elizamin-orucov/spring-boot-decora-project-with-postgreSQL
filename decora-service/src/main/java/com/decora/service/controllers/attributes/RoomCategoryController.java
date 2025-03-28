package com.decora.service.controllers.attributes;

import com.decora.service.controllers.base.BaseController;
import com.decora.service.dtos.response.ApiResponseDto;
import com.decora.service.dtos.room_category.RoomCategoryCreateDto;
import com.decora.service.dtos.room_category.RoomCategoryDto;
import com.decora.service.dtos.room_category.RoomCategoryListDto;
import com.decora.service.dtos.room_category.RoomCategoryUpdateDto;
import com.decora.service.services.RoomCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room/category")
public class RoomCategoryController implements BaseController<
        RoomCategoryDto,
        RoomCategoryListDto,
        RoomCategoryCreateDto,
        RoomCategoryUpdateDto
        > {
    private final RoomCategoryService categoryService;

    public RoomCategoryController(RoomCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<RoomCategoryListDto>> fetchAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        page = Math.abs(page);
        size = Math.abs(size);
        Page<RoomCategoryListDto> listDtos = categoryService.list(PageRequest.of(page -1, size));
        return ResponseEntity.ok(listDtos);
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponseDto<RoomCategoryDto>> create(@ModelAttribute RoomCategoryCreateDto createDto) {
        ApiResponseDto<RoomCategoryDto> responseDto = categoryService.create(createDto);
        return ResponseEntity.ok(responseDto);
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponseDto<RoomCategoryUpdateDto>> update(RoomCategoryUpdateDto updateDto) {
        ApiResponseDto<RoomCategoryUpdateDto> responseDto = categoryService.update(updateDto);
        return ResponseEntity.ok(responseDto);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponseDto<RoomCategoryDto>> delete(Long id) {
        ApiResponseDto<RoomCategoryDto> response = categoryService.delete(id);
        return ResponseEntity.ok(response);
    }
}

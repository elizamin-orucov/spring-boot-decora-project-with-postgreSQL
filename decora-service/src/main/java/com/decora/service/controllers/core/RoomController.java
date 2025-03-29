package com.decora.service.controllers.core;

import com.decora.service.controllers.base.BaseController;
import com.decora.service.dtos.response.ApiResponseDto;
import com.decora.service.dtos.room.RoomCreateDto;
import com.decora.service.dtos.room.RoomDto;
import com.decora.service.dtos.room.RoomListDto;
import com.decora.service.dtos.room.RoomUpdateDto;
import com.decora.service.services.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/room")
public class RoomController implements BaseController<
        RoomDto,
        RoomListDto,
        RoomCreateDto,
        RoomUpdateDto
        > {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @Override
    @GetMapping
    public ResponseEntity<Page<RoomListDto>> fetchAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        page = Math.abs(page);
        size = Math.abs(size);

        Page<RoomListDto> listDtos = roomService.list(PageRequest.of(page - 1, size));
        return ResponseEntity.ok(listDtos);
    }

    @Override
    @GetMapping("/{slug}")
    public ResponseEntity<RoomDto> fetchBySlug(
            @PathVariable String slug
    ) {
        RoomDto roomDto = roomService.detail(slug);
        return ResponseEntity.ok(roomDto);
    }

    @Override
    public ResponseEntity<ApiResponseDto<RoomDto>> create(@ModelAttribute RoomCreateDto dto) {
        ApiResponseDto<RoomDto> response = roomService.create(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<RoomDto>> create(
            @ModelAttribute RoomCreateDto dto,
            @RequestParam("images") MultipartFile[] images
            ) {
        ApiResponseDto<RoomDto> response = roomService.create(dto, images);
        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponseDto<RoomUpdateDto>> update(
            @ModelAttribute RoomUpdateDto dto
    ) {
        ApiResponseDto<RoomUpdateDto> responseDto = roomService.update(dto);
        return ResponseEntity.ok(responseDto);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponseDto<RoomDto>> delete(Long id) {
        ApiResponseDto<RoomDto> response = roomService.delete(id);
        return ResponseEntity.ok(response);
    }
}

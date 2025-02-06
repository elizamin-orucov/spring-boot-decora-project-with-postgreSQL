package com.decora.service.controllers.base;

import com.decora.service.dtos.response.ApiResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface BaseController<
        EntityDetailDto,
        EntityListDto,
        EntityCreateDto,
        EntityUpdateDto
        > {
    ResponseEntity<Page<EntityListDto>> fetchAll(int page, int size);
    ResponseEntity<EntityDetailDto> fetchById(Long id);
    ResponseEntity<ApiResponseDto<EntityDetailDto>> create(EntityCreateDto createDto);
    ResponseEntity<ApiResponseDto<EntityUpdateDto>> update(EntityUpdateDto updateDto);
    ResponseEntity<ApiResponseDto<EntityDetailDto>> delete(Long id);
}

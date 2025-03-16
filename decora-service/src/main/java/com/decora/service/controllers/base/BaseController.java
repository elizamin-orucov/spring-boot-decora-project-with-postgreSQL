package com.decora.service.controllers.base;

import com.decora.service.dtos.response.ApiResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface BaseController<
        EntityDetailDto,
        EntityListDto,
        EntityCreateDto,
        EntityUpdateDto
        > {
    ResponseEntity<Page<EntityListDto>> fetchAll(int page, int size);
    ResponseEntity<EntityDetailDto> fetchById(Long id);
    default ResponseEntity<EntityDetailDto> fetchBySlug(Long id){return null;};
    ResponseEntity<ApiResponseDto<EntityDetailDto>> create(@Valid @RequestBody EntityCreateDto createDto);
    ResponseEntity<ApiResponseDto<EntityUpdateDto>> update(EntityUpdateDto updateDto);
    ResponseEntity<ApiResponseDto<EntityDetailDto>> delete(Long id);
}

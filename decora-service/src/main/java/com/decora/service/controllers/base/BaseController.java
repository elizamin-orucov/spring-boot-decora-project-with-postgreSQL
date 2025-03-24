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
    default ResponseEntity<EntityDetailDto> fetchById(Long id){return null;};
    default ResponseEntity<EntityDetailDto> fetchBySlug(String slug){return null;};
    ResponseEntity<ApiResponseDto<EntityDetailDto>> create(@Valid @RequestBody EntityCreateDto createDto);
    ResponseEntity<ApiResponseDto<EntityUpdateDto>> update(EntityUpdateDto updateDto);
    ResponseEntity<ApiResponseDto<EntityDetailDto>> delete(Long id);
}

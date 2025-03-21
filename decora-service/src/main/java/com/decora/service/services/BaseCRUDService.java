package com.decora.service.services;

import com.decora.service.dtos.response.ApiResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface BaseCRUDService<
        EntityDetailDto, EntityListDto, EntityCreateDto, EntityUpdateDto
        > {

    Page<EntityListDto> list(Pageable pageable);

    default List<EntityListDto> list(){
        return new ArrayList<>();
    };

    EntityDetailDto detail(Long id);

    ApiResponseDto<EntityDetailDto> create(EntityCreateDto dto);
    default ApiResponseDto<EntityDetailDto> create(EntityCreateDto dto, MultipartFile[] files){
        return null;
    }

    ApiResponseDto<EntityUpdateDto> update(EntityUpdateDto dto);

    ApiResponseDto<EntityDetailDto> delete(Long id);

}

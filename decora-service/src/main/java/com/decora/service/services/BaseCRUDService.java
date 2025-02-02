package com.decora.service.services;

import com.decora.service.dtos.response.ApiResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface BaseCRUDService<DetailDto, ListDto, CreateDto, UpdateDto> {

    Page<ListDto> list(Pageable pageable);

    default List<ListDto> list(){
        return new ArrayList<>();
    };

    DetailDto detail(Long id);

    ApiResponseDto<DetailDto> create(CreateDto dto);

    ApiResponseDto<UpdateDto> update(UpdateDto dto);

    ApiResponseDto<DetailDto> delete(Long id);

}

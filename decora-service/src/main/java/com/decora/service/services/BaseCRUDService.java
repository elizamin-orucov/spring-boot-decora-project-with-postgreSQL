package com.decora.service.services;

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

    String create(CreateDto dto);

    String update(UpdateDto dto);

    String delete(Long id);

}

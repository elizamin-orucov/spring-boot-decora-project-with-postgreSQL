package com.decora.service.services.Impl;

import com.decora.service.dtos.response.ApiResponseDto;
import com.decora.service.dtos.room_category.RoomCategoryCreateDto;
import com.decora.service.dtos.room_category.RoomCategoryDto;
import com.decora.service.dtos.room_category.RoomCategoryListDto;
import com.decora.service.dtos.room_category.RoomCategoryUpdateDto;
import com.decora.service.mappers.attributes.RoomCategoryMapper;
import com.decora.service.models.attributes.RoomCategoryEntity;
import com.decora.service.models.core.rooms.RoomEntity;
import com.decora.service.repositories.attributes.RoomCategoryRepository;
import com.decora.service.services.RoomCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class RoomCategoryServiceImpl implements RoomCategoryService {
    private final RoomCategoryRepository categoryRepository;

    public RoomCategoryServiceImpl(RoomCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<RoomCategoryListDto> list(Pageable pageable) {
        Page<RoomCategoryEntity> entities = categoryRepository.findAll(pageable);
        return entities.map(RoomCategoryMapper.INSTANCE::toListDto);
    }

    @Override
    public RoomCategoryDto detail(Long id) {
        RoomCategoryEntity entity = getRoomCategoryEntity(id);
        return RoomCategoryMapper.INSTANCE.toDto(entity);
    }

    @Override
    @Transactional
    public ApiResponseDto<RoomCategoryDto> create(RoomCategoryCreateDto roomCategoryCreateDto) {
        RoomCategoryEntity entity = RoomCategoryMapper.INSTANCE.toEntity(roomCategoryCreateDto);

        RoomCategoryEntity savedEntity = categoryRepository.save(entity);
        return ApiResponseDto.<RoomCategoryDto>builder()
                .success(true)
                .message("category saved success")
                .response(RoomCategoryMapper.INSTANCE.toDto(savedEntity))
                .build();
    }

    @Override
    @Transactional
    public ApiResponseDto<RoomCategoryUpdateDto> update(RoomCategoryUpdateDto roomCategoryUpdateDto) {
        RoomCategoryEntity entity = RoomCategoryMapper.INSTANCE.toEntity(roomCategoryUpdateDto);

        categoryRepository.save(entity);
        return ApiResponseDto.<RoomCategoryUpdateDto>builder()
                .success(true)
                .message("category updated success")
                .response(roomCategoryUpdateDto)
                .build();
    }

    @Override
    @Transactional
    public ApiResponseDto<RoomCategoryDto> delete(Long id) {
        RoomCategoryEntity entity = getRoomCategoryEntity(id);
        if(entity == null){
            log.warn("Category with id {} not found", id);
            return ApiResponseDto.<RoomCategoryDto>builder()
                    .success(false)
                    .message("category id not found")
                    .build();
        }

        categoryRepository.delete(entity);
        log.info("Category with id {} deleted successfully", id);
        RoomCategoryDto categoryDto = RoomCategoryMapper.INSTANCE.toDto(entity);
        return ApiResponseDto.<RoomCategoryDto>builder()
                .success(true)
                .message(String.format("category with id %d deleted", id))
                .response(categoryDto)
                .build();
    }

    private RoomCategoryEntity getRoomCategoryEntity(Long id){
        log.debug("Fetching room category entity for id: {}", id);
        Optional<RoomCategoryEntity> entityOptional = categoryRepository.findById(id);
        if (entityOptional.isEmpty()){
            log.warn("Room room category not found for id: {}", id);
        }
        return entityOptional.orElse(null);
    }
}

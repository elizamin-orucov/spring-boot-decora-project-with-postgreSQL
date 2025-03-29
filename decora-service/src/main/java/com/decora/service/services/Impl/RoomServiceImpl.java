package com.decora.service.services.Impl;

import com.decora.service.dtos.response.ApiResponseDto;
import com.decora.service.dtos.room.RoomCreateDto;
import com.decora.service.dtos.room.RoomDto;
import com.decora.service.dtos.room.RoomListDto;
import com.decora.service.dtos.room.RoomUpdateDto;
import com.decora.service.mappers.core.RoomMapper;
import com.decora.service.models.attributes.RoomCategoryEntity;
import com.decora.service.models.core.product.ProductEntity;
import com.decora.service.models.core.rooms.RoomEntity;
import com.decora.service.repositories.attributes.RoomCategoryRepository;
import com.decora.service.repositories.core.ProductRepository;
import com.decora.service.repositories.core.RoomRepository;
import com.decora.service.services.RoomImageService;
import com.decora.service.services.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static com.decora.service.services.Impl.CreateSlugService.createSlug;

@Slf4j
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomCategoryRepository categoryRepository;
    private final RoomImageService imageService;
    private final ProductRepository productRepository;

    public RoomServiceImpl(RoomRepository roomRepository, RoomCategoryRepository categoryRepository, RoomImageService imageService, ProductRepository productRepository) {
        this.roomRepository = roomRepository;
        this.categoryRepository = categoryRepository;
        this.imageService = imageService;
        this.productRepository = productRepository;
    }

    @Override
    public Page<RoomListDto> list(Pageable pageable) {
        Page<RoomEntity> entities = roomRepository.findAll(pageable);
        return entities.map(RoomMapper.INSTANCE::toListDto);
    }

    @Override
    public RoomDto detail(Long id) {
        RoomEntity entity = getRoomEntity(id);
        return RoomMapper.INSTANCE.toDto(entity);
    }

    @Override
    public RoomDto detail(String slug) {
        RoomEntity entity = getRoomEntity(slug);
        return RoomMapper.INSTANCE.toDto(entity);
    }

    @Override
    @Transactional
    public ApiResponseDto<RoomDto> create(RoomCreateDto roomCreateDto) {
        RoomEntity entity = RoomMapper.INSTANCE.toEntity(roomCreateDto);

        entity.setSlug(generateUniqueSlug(roomCreateDto.getTitle()));
        RoomCategoryEntity roomCategoryEntity = categoryRepository.findById(roomCreateDto.getCategoryID()).orElse(null);

        entity.setCategory(roomCategoryEntity);

        RoomEntity savedEntity = roomRepository.save(entity);


        RoomDto roomDto = RoomMapper.INSTANCE.toDto(savedEntity);
        return ApiResponseDto.<RoomDto>builder()
                .message("room saved success")
                .response(roomDto)
                .build();
    }

    @Override
    @Transactional
    public ApiResponseDto<RoomDto> create(RoomCreateDto roomCreateDto, MultipartFile[] files) {
        RoomEntity entity = RoomMapper.INSTANCE.toEntity(roomCreateDto);

        entity.setSlug(generateUniqueSlug(roomCreateDto.getTitle()));

        RoomCategoryEntity roomCategoryEntity = categoryRepository.findById(roomCreateDto.getCategoryID()).orElse(null);
        List<ProductEntity> productEntities = productRepository.findByIdIn(roomCreateDto.getProductIDS());

        entity.setCategory(roomCategoryEntity);
        entity.setProducts(productEntities);

        RoomEntity savedEntity = roomRepository.save(entity);

        imageService.saveImages(savedEntity, files);

        return ApiResponseDto.<RoomDto>builder()
                .success(true)
                .message("room saved success")
                .response(RoomMapper.INSTANCE.toDto(savedEntity))
                .build();
    }

    @Override
    @Transactional
    public ApiResponseDto<RoomUpdateDto> update(RoomUpdateDto roomUpdateDto) {
        RoomEntity existingRoom = roomRepository.findById(roomUpdateDto.getId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        RoomMapper.INSTANCE.updateEntityFromDto(roomUpdateDto, existingRoom);

        existingRoom.setSlug(generateUniqueSlug(roomUpdateDto.getTitle()));

        roomRepository.save(existingRoom);
        return ApiResponseDto.<RoomUpdateDto>builder()
                .success(true)
                .message("updated room")
                .response(roomUpdateDto)
                .build();
    }

    @Override
    @Transactional
    public ApiResponseDto<RoomDto> delete(Long id) {
        RoomEntity roomEntity = getRoomEntity(id);

        if (roomEntity == null){
            log.warn("Room with id {} not found", id);
            return ApiResponseDto.<RoomDto>builder()
                    .success(false)
                    .message("room not found")
                    .build();
        }

        roomRepository.delete(roomEntity);
        log.info("Room with id {} deleted successfully", id);
        RoomDto roomDto = RoomMapper.INSTANCE.toDto(roomEntity);
        return ApiResponseDto.<RoomDto>builder()
                .success(true)
                .message(String.format("room with id %d deleted", id))
                .response(roomDto)
                .build();
    }

    private RoomEntity getRoomEntity(Long id){
        log.debug("Fetching room entity for id: {}", id);
        System.out.println(id);
        Optional<RoomEntity> entityOptional = roomRepository.findById(id);
        if (entityOptional.isEmpty()){
            log.warn("Room entity not found for id: {}", id);
        }
        return entityOptional.orElse(null);
    }

    private RoomEntity getRoomEntity(String slug){
        log.debug("Fetching room entity for slug: {}", slug);
        Optional<RoomEntity> entityOptional = roomRepository.findBySlug(slug);
        if (entityOptional.isEmpty()){
            log.warn("Room room not found for slug: {}", slug);
        }
        return entityOptional.orElse(null);
    }

    private String generateUniqueSlug(String input) {

        String baseSlug = createSlug(input);
        String uniqueSlug = baseSlug;
        int counter = 1;

        while (roomRepository.existsBySlug(uniqueSlug)) {
            uniqueSlug = baseSlug + "-" + counter;
            counter++;
        }

        return uniqueSlug;
    }
}

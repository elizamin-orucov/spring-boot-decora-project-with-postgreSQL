package com.decora.service.services.Impl;

import com.decora.service.dtos.response.ApiResponseDto;
import com.decora.service.dtos.room.RoomImageListDto;
import com.decora.service.mappers.core.RoomImageMapper;
import com.decora.service.models.core.rooms.RoomEntity;
import com.decora.service.models.core.rooms.RoomImageEntity;
import com.decora.service.repositories.attributes.RoomImageRepository;
import com.decora.service.repositories.core.RoomRepository;
import com.decora.service.services.RoomImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomImageServiceImpl implements RoomImageService {
    @Value("${baseRoomImageFolder}")
    private String UPLOAD_DIR;

    private final RoomRepository roomRepository;
    private final RoomImageRepository imageRepository;

    public RoomImageServiceImpl(RoomRepository roomRepository, RoomImageRepository imageRepository) {
        this.roomRepository = roomRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    @Transactional
    public String saveImage(RoomEntity entity, MultipartFile image) throws IOException {
        try {
            byte[] bytes = image.getBytes();

            Path path = Paths.get(UPLOAD_DIR + entity.getId() + "/" + image.getOriginalFilename());
            Files.createDirectories(path.getParent());

            Files.write(path, bytes);

            RoomImageEntity imageEntity = new RoomImageEntity();

            imageEntity.setRoom(entity);
            imageEntity.setImageUrl(path.toString().replace("\\", "/"));

            return "image saved success";
        } catch (IOException e){
            return e.getMessage();
        }
    }

    @Override
    @Transactional
    public String saveImages(RoomEntity entity, MultipartFile[] images) {
        UPLOAD_DIR = UPLOAD_DIR  + LocalDate.now() + "/";
        try {
            List<RoomImageEntity> imageEntityList = new ArrayList<>();
            if (images != null) {
                for (MultipartFile file: images) {

                    byte[] bytes = file.getBytes();

                    Path path = Paths.get(UPLOAD_DIR + entity.getId() + "/" + file.getOriginalFilename());
                    Files.createDirectories(path.getParent());

                    Files.write(path, bytes);

                    RoomImageEntity imageEntity = new RoomImageEntity();

                    imageEntity.setRoom(entity);
                    imageEntity.setImageUrl(path.toString().replace("\\", "/"));

                    imageEntityList.add(imageEntity);
                }
                entity.setImages(imageEntityList);
                roomRepository.save(entity);
            }
            return "image saved success";

        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    public String deleteImage(String filePath) {
        try {
            Path path = Paths.get(filePath);
            Files.deleteIfExists(path);
            return "deleted image success";
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    @Transactional
    public ApiResponseDto<RoomImageListDto> deleteImage(Long imageId) {
        RoomImageEntity imageEntity = getImageEntity(imageId);
        if (imageEntity != null && imageEntity.getImageUrl() != null){
            RoomImageListDto imageListDto = RoomImageMapper.INSTANCE.toListDto(imageEntity);
            deleteImage(imageEntity.getImageUrl());
            imageRepository.delete(imageEntity);
            return ApiResponseDto.<RoomImageListDto>builder()
                    .response(imageListDto)
                    .message("delete image success")
                    .build();
        }
        return ApiResponseDto.<RoomImageListDto>builder()
                .message("not found image")
                .response(null)
                .build();
    }

    @Override
    public byte[] readImage(Long imageID) {
        try {
            RoomImageEntity imageEntity = getImageEntity(imageID);
            if (imageEntity != null && imageEntity.getImageUrl() != null) {
                return Files.readAllBytes(Paths.get(imageEntity.getImageUrl()));
            }
            else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private RoomImageEntity getImageEntity(Long imageID){
        Optional<RoomImageEntity> optionalRoomImage = imageRepository.findById(imageID);
        return optionalRoomImage.orElse(null);
    }
}

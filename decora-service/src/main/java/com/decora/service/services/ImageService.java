package com.decora.service.services;

import com.decora.service.dtos.response.ApiResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService<Entity, ResponseDto> {
    // save methods
    String saveImage(Entity entity, MultipartFile image) throws IOException;

    default String saveImages(Entity entity, MultipartFile[] images) {
        return null;
    }

    // destroy methods
    String deleteImage(String imageId);

    ApiResponseDto<ResponseDto> deleteImage(Long imageId);

    default String deleteImages(List<String> imageIds){return null;}

    // read methods
    byte[] readImage(Long imageID);
}

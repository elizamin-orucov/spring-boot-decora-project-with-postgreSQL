package com.decora.service.services;

import com.decora.service.dtos.product.ProductImageListDto;
import com.decora.service.dtos.response.ApiResponseDto;
import com.decora.service.models.core.product.ProductEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductImageService {
    // save methods
    String saveImage(ProductEntity productEntity, MultipartFile image) throws IOException;

    default String saveImages(ProductEntity productEntity, MultipartFile[] images) {
        return null;
    }

    // destroy methods
    String deleteImage(String imageId);

    ApiResponseDto<ProductImageListDto> deleteImage(Long imageId);

    default String deleteImages(List<String> imageIds){return null;}

    // read methods
    byte[] readImage(Long imageID);
}

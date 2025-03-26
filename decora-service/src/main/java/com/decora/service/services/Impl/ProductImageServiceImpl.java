package com.decora.service.services.Impl;

import com.decora.service.dtos.product.ProductImageListDto;
import com.decora.service.dtos.response.ApiResponseDto;
import com.decora.service.mappers.core.ProductImageMapper;
import com.decora.service.models.core.product.ProductEntity;
import com.decora.service.models.core.product.ProductImageEntity;
import com.decora.service.repositories.attributes.ProductImageRepository;
import com.decora.service.repositories.core.ProductRepository;
import com.decora.service.services.ProductImageService;
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
public class ProductImageServiceImpl implements ProductImageService {
    @Value("${baseProductImageFolder}")
    private String UPLOAD_DIR;

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductImageServiceImpl(ProductRepository repository, ProductImageRepository productImageRepository) {
        this.productRepository = repository;
        this.productImageRepository = productImageRepository;
    }

    @Override
    @Transactional
    public String saveImage(ProductEntity productEntity, MultipartFile image) {
        try {
            byte[] bytes = image.getBytes();

            Path path = Paths.get(UPLOAD_DIR + productEntity.getId() + "/" + image.getOriginalFilename());
            Files.createDirectories(path.getParent());

            Files.write(path, bytes);

            ProductImageEntity imageEntity = new ProductImageEntity();

            imageEntity.setProduct(productEntity);
            imageEntity.setImageUrl(path.toString().replace("\\", "/"));

            return "image saved success";
        } catch (IOException e){
            return e.getMessage();
        }
    }

    @Override
    @Transactional
    public String saveImages(ProductEntity productEntity, MultipartFile[] images) {
        UPLOAD_DIR = UPLOAD_DIR  + LocalDate.now() + "/";
        try {
            List<ProductImageEntity> imageEntityList = new ArrayList<>();
            if (images != null) {
                for (MultipartFile file: images) {

                    byte[] bytes = file.getBytes();

                    Path path = Paths.get(UPLOAD_DIR + productEntity.getId() + "/" + file.getOriginalFilename());
                    Files.createDirectories(path.getParent());

                    Files.write(path, bytes);

                    ProductImageEntity imageEntity = new ProductImageEntity();

                    imageEntity.setProduct(productEntity);
                    imageEntity.setImageUrl(path.toString().replace("\\", "/"));

                    imageEntityList.add(imageEntity);
                }
                productEntity.setImages(imageEntityList);
                productRepository.save(productEntity);
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

//    @Transactional
//    public String deleteImage(Long imageId) {
//        ProductImageEntity imageEntity = getImageEntity(imageId);
//        if (imageEntity != null && imageEntity.getImageUrl() != null) {
//            deleteImage(imageEntity.getImageUrl());
//            productImageRepository.delete(imageEntity);
//        }
//        return "file not found";
//    }

    @Override
    @Transactional
    public ApiResponseDto<ProductImageListDto> deleteImage(Long imageId) {
        ProductImageEntity imageEntity = getImageEntity(imageId);
        if (imageEntity != null && imageEntity.getImageUrl() != null){
            ProductImageListDto imageListDto = ProductImageMapper.INSTANCE.toListDto(imageEntity);
            deleteImage(imageEntity.getImageUrl());
            productImageRepository.delete(imageEntity);
            return ApiResponseDto.<ProductImageListDto>builder()
                    .response(imageListDto)
                    .message("delete image success")
                    .build();
        }
        return ApiResponseDto.<ProductImageListDto>builder()
                .message("not found image")
                .response(null)
                .build();
    }

    @Override
    @Transactional
    public String deleteImages(List<String> imageIds) {
        return ProductImageService.super.deleteImages(imageIds);
    }

    @Override
    public byte[] readImage(Long imageID) {
        try {
            ProductImageEntity imageEntity = getImageEntity(imageID);
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

    private ProductImageEntity getImageEntity(Long imageID){
        Optional<ProductImageEntity> optionalProductImage = productImageRepository.findById(imageID);
        return optionalProductImage.orElse(null);
    }
}

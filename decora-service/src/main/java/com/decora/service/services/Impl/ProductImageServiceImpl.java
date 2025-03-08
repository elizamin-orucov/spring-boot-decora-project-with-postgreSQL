package com.decora.service.services.Impl;

import com.decora.service.models.core.product.ProductEntity;
import com.decora.service.models.core.product.ProductImageEntity;
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

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Value("${baseProductImageFolder}")
    private String UPLOAD_DIR;

    private final ProductRepository productRepository;

    public ProductImageServiceImpl(ProductRepository repository) {
        this.productRepository = repository;
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
    @Transactional
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
    public String deleteImages(List<String> imageIds) {
        return ProductImageService.super.deleteImages(imageIds);
    }

    @Override
    public byte[] readImage(String imagePath) {
        try {
            if (imagePath != null) {
                return Files.readAllBytes(Paths.get(imagePath));
            }
            else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

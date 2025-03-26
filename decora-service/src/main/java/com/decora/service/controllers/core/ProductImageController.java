package com.decora.service.controllers.core;

import com.decora.service.dtos.product.ProductImageListDto;
import com.decora.service.dtos.response.ApiResponseDto;
import com.decora.service.services.ProductImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/image")
public class ProductImageController {
    private final ProductImageService imageService;

    public ProductImageController(ProductImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<byte[]> getImage(
            @RequestParam("id") Long imageID
    ){
        byte[] image = imageService.readImage(imageID);
        return ResponseEntity.ok(image);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseDto<ProductImageListDto>> deleteImage(
            @RequestParam("id") Long imageID
    ){
        ApiResponseDto<ProductImageListDto> response = imageService.deleteImage(imageID);

        return ResponseEntity.ok(response);
    }
}

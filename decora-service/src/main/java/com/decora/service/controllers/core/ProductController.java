package com.decora.service.controllers.core;

import com.decora.service.dtos.product.ProductCreateDto;
import com.decora.service.dtos.product.ProductDto;
import com.decora.service.dtos.product.ProductListDto;
import com.decora.service.dtos.response.ApiResponseDto;
import com.decora.service.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductListDto>> fetchAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        page = Math.abs(page);
        size = Math.abs(size);
        Pageable pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(productService.list(pageable));
    }

    @PostMapping(value = "/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ApiResponseDto<ProductDto>> create(
            @RequestBody ProductCreateDto product,
            @RequestBody MultipartFile[] images
            ){
        ApiResponseDto<ProductDto> response = productService.create(product, images);
        return ResponseEntity.ok(response);
    }
}

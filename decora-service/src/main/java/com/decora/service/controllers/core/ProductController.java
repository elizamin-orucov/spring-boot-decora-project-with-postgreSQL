package com.decora.service.controllers.core;

import com.decora.service.controllers.base.BaseController;
import com.decora.service.dtos.product.ProductCreateDto;
import com.decora.service.dtos.product.ProductDto;
import com.decora.service.dtos.product.ProductListDto;
import com.decora.service.dtos.product.ProductUpdateDto;
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
public class ProductController implements BaseController<
        ProductDto,
        ProductListDto,
        ProductCreateDto,
        ProductUpdateDto
        > {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ProductListDto>> fetchAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        page = Math.abs(page);
        size = Math.abs(size);
        Page<ProductListDto> productListDtos = productService.list(PageRequest.of(page - 1, size));
        return ResponseEntity.ok(productListDtos);
    }

    @Override
    public ResponseEntity<ProductDto> fetchById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponseDto<ProductDto>> create(@ModelAttribute ProductCreateDto product) {
        ApiResponseDto<ProductDto> response = productService.create(product);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ApiResponseDto<ProductDto>> create(
            @ModelAttribute ProductCreateDto product,
            @RequestParam("images") MultipartFile[] images
    ) {
        ApiResponseDto<ProductDto> response = productService.create(product, images);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseDto<ProductUpdateDto>> update(ProductUpdateDto productUpdateDto) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponseDto<ProductDto>> delete(Long id) {
        return null;
    }

}

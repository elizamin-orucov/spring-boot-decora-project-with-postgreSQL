package com.decora.service.controllers.attributes;

import com.decora.service.controllers.base.BaseController;
import com.decora.service.dtos.product_category.ProductCategoryCreateDto;
import com.decora.service.dtos.product_category.ProductCategoryDto;
import com.decora.service.dtos.product_category.ProductCategoryListDto;
import com.decora.service.dtos.product_category.ProductCategoryUpdateDto;
import com.decora.service.dtos.response.ApiResponseDto;
import com.decora.service.services.ProductCategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/category")
public class ProductCategoryController implements BaseController<
        ProductCategoryDto,
        ProductCategoryListDto,
        ProductCategoryCreateDto,
        ProductCategoryUpdateDto
        > {

    private final ProductCategoryService categoryService;

    public ProductCategoryController(
            ProductCategoryService categoryService
    ) {
        this.categoryService = categoryService;
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ProductCategoryListDto>> fetchAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        page = Math.abs(page);
        size = Math.abs(size);
        Page<ProductCategoryListDto> listDto = categoryService.list(PageRequest.of(page - 1, size));
        return ResponseEntity.status(HttpStatus.OK).body(listDto);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDto> fetchById(@PathVariable Long id) {
        ProductCategoryDto categoryDto = categoryService.detail(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponseDto<ProductCategoryDto>> create(
            @Valid @RequestBody ProductCategoryCreateDto productCategoryCreateDto
    ) {
        ApiResponseDto<ProductCategoryDto> createNewCategory = categoryService.create(productCategoryCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createNewCategory);
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponseDto<ProductCategoryUpdateDto>> update(
            @RequestBody ProductCategoryUpdateDto productCategoryUpdateDto
    ) {
        ApiResponseDto<ProductCategoryUpdateDto> updateDtoResponse = categoryService.update(productCategoryUpdateDto);
        return ResponseEntity.ok(updateDtoResponse);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ProductCategoryDto>> delete(
            @PathVariable Long id
    ) {
        ApiResponseDto<ProductCategoryDto> deleteDtoResponse = categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleteDtoResponse);
    }
}

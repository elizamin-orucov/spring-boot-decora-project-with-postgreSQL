package com.decora.service.controllers.attributes;

import com.decora.service.controllers.base.BaseController;
import com.decora.service.dtos.product_color.ProductColorCreateDto;
import com.decora.service.dtos.product_color.ProductColorDto;
import com.decora.service.dtos.product_color.ProductColorListDto;
import com.decora.service.dtos.product_color.ProductColorUpdateDto;
import com.decora.service.dtos.response.ApiResponseDto;
import com.decora.service.services.ProductColorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/color")
public class ProductColorController implements BaseController<
        ProductColorDto,
        ProductColorListDto,
        ProductColorCreateDto,
        ProductColorUpdateDto
        > {
    private final ProductColorService colorService;

    public ProductColorController(ProductColorService colorService) {
        this.colorService = colorService;
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ProductColorListDto>> fetchAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ProductColorListDto> listDto = colorService.list(PageRequest.of(page - 1, size));
        return ResponseEntity.status(HttpStatus.OK).body(listDto);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProductColorDto> fetchById(@PathVariable Long id) {
        ProductColorDto colorDto = colorService.detail(id);
        return ResponseEntity.status(HttpStatus.OK).body(colorDto);
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponseDto<ProductColorDto>> create(
            @Valid @RequestBody ProductColorCreateDto productColorCreateDto
    ) {
        ApiResponseDto<ProductColorDto> createNewColor = colorService.create(productColorCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createNewColor);
    }

    @Override
    @PatchMapping
    public ResponseEntity<ApiResponseDto<ProductColorUpdateDto>> update(
            @RequestBody ProductColorUpdateDto productColorUpdateDto
    ) {
        ApiResponseDto<ProductColorUpdateDto> updateColorResponse = colorService.update(productColorUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).body(updateColorResponse);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ProductColorDto>> delete(
            @PathVariable Long id
    ) {
        ApiResponseDto<ProductColorDto> deletedDtoResponse = colorService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedDtoResponse);
    }
}

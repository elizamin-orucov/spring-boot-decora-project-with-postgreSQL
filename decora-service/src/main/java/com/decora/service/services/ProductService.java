package com.decora.service.services;

import com.decora.service.dtos.product.ProductCreateDto;
import com.decora.service.dtos.product.ProductDto;
import com.decora.service.dtos.product.ProductListDto;
import com.decora.service.dtos.product.ProductUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService extends BaseCRUDService<
        ProductDto,
        ProductListDto,
        ProductCreateDto,
        ProductUpdateDto
        >{
    ProductDto detail(String slug);
    Page<ProductListDto> list(
            Pageable pageable, Long categoryId, List<Long> colorIds, String title, Double minPrice, Double maxPrice, String sortBy, String sortDirection);
}

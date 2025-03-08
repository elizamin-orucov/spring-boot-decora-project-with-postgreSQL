package com.decora.service.services.Impl;

import com.decora.service.dtos.product.ProductCreateDto;
import com.decora.service.dtos.product.ProductDto;
import com.decora.service.dtos.product.ProductListDto;
import com.decora.service.dtos.product.ProductUpdateDto;
import com.decora.service.dtos.response.ApiResponseDto;
import com.decora.service.mappers.core.ProductMapper;
import com.decora.service.models.core.product.ProductEntity;
import com.decora.service.repositories.core.ProductRepository;
import com.decora.service.services.ProductImageService;
import com.decora.service.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static com.decora.service.services.Impl.CreateSlugService.createSlug;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductImageService imageService;

    public ProductServiceImpl(ProductRepository productRepository, ProductImageService imageService) {
        this.productRepository = productRepository;
        this.imageService = imageService;
    }

    @Override
    public Page<ProductListDto> list(Pageable pageable) {
        Page<ProductEntity> entities = productRepository.findAll(pageable);
        return entities.map(ProductMapper.INSTANCE::toListDto);
    }

    @Override
    public ProductDto detail(Long id) {
        log.info("Fetching product details for id: {}", id);
        ProductEntity entity = getProductEntity(id);
        return ProductMapper.INSTANCE.toDto(entity);
    }

    @Override
    @Transactional
    public ApiResponseDto<ProductDto> create(ProductCreateDto dto) {
        ProductEntity productEntity = ProductMapper.INSTANCE.toEntity(dto);

        productEntity.setSlug(generateUniqueSlug(productEntity.getTitle()));

        ProductEntity savedEntity = productRepository.save(productEntity);
        return ApiResponseDto.<ProductDto>builder()
                .success(true)
                .message("product saved success")
                .response(ProductMapper.INSTANCE.toDto(savedEntity))
                .build();
    }

    @Transactional
    public ApiResponseDto<ProductDto> create(
            ProductCreateDto productCreateDto,
            MultipartFile[] images
    ){
        ProductEntity productEntity = ProductMapper.INSTANCE.toEntity(productCreateDto);

        productEntity.setSlug(generateUniqueSlug(productEntity.getTitle()));

        ProductEntity savedEntity = productRepository.save(productEntity);

        imageService.saveImages(savedEntity, images);

        return ApiResponseDto.<ProductDto>builder()
                .success(true)
                .message("product saved success")
                .response(ProductMapper.INSTANCE.toDto(savedEntity))
                .build();
    }

    @Override
    public ApiResponseDto<ProductUpdateDto> update(ProductUpdateDto productUpdateDto) {
        ProductEntity entity = ProductMapper.INSTANCE.toEntity(productUpdateDto);
        productRepository.save(entity);
        return ApiResponseDto.<ProductUpdateDto>builder()
                .success(true)
                .message("updated product")
                .response(productUpdateDto)
                .build();
    }

    @Override
    public ApiResponseDto<ProductDto> delete(Long id) {
        log.info("Deleting product with id: {}", id);
        ProductEntity entity = getProductEntity(id);

        if (entity == null){
            log.warn("Product with id {} not found", id);
            return ApiResponseDto.<ProductDto>builder()
                    .success(false)
                    .message("product not found")
                    .build();
        }

        productRepository.delete(entity);
        log.info("Product with id {} deleted successfully", id);
        ProductDto productDto = ProductMapper.INSTANCE.toDto(entity);
        return ApiResponseDto.<ProductDto>builder()
                .success(true)
                .message(String.format("product with id %d deleted", id))
                .response(productDto)
                .build();
    }

    private ProductEntity getProductEntity(Long id){
        log.debug("Fetching product entity for id: {}", id);
        Optional<ProductEntity> entityOptional = productRepository.findById(id);
        if (entityOptional.isEmpty()){
            log.warn("Product entity not found for id: {}", id);
        }
        return entityOptional.orElse(null);
    }

    private ProductEntity getProductEntity(String slug){
        log.debug("Fetching product entity for slug: {}", slug);
        Optional<ProductEntity> entityOptional = productRepository.findBySlug(slug);
        if (entityOptional.isEmpty()){
            log.warn("Product entity not found for slug: {}", slug);
        }
        return entityOptional.orElse(null);
    }

    private String generateUniqueSlug(String input) {

        String uniqueSlug = createSlug(input);
        int counter = 1;
        while (productRepository.existsBySlug(uniqueSlug)) {
            uniqueSlug = uniqueSlug + "-" + counter;
            counter++;
        }

        return uniqueSlug;
    }
}

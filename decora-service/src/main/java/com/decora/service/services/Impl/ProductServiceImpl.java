package com.decora.service.services.Impl;

import com.decora.service.dtos.product.ProductCreateDto;
import com.decora.service.dtos.product.ProductDto;
import com.decora.service.dtos.product.ProductListDto;
import com.decora.service.dtos.product.ProductUpdateDto;
import com.decora.service.dtos.response.ApiResponseDto;
import com.decora.service.mappers.core.ProductMapper;
import com.decora.service.models.attributes.ProductCategoryEntity;
import com.decora.service.models.attributes.ProductColorEntity;
import com.decora.service.models.core.product.ProductEntity;
import com.decora.service.repositories.attributes.ProductCategoryRepository;
import com.decora.service.repositories.attributes.ProductColorRepository;
import com.decora.service.repositories.core.ProductRepository;
import com.decora.service.services.ProductImageService;
import com.decora.service.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    private final ProductCategoryRepository categoryRepository;

    private final ProductColorRepository colorRepository;

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductImageService imageService, ProductCategoryRepository categoryRepository, ProductColorRepository colorRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.imageService = imageService;
        this.categoryRepository = categoryRepository;
        this.colorRepository = colorRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Page<ProductListDto> list(Pageable pageable) {
        Page<ProductEntity> entities = productRepository.findAll(pageable);
        return entities.map(ProductMapper.INSTANCE::toListDto);
    }

    public Page<ProductListDto> list(
            Pageable pageable, Long categoryId, List<Long> colorIds, String title, Double minPrice, Double maxPrice, String sortBy, String sortDirection) {
        Specification<ProductEntity> spec = Specification.where(ProductSpecification.hasCategory(categoryId))
                .and(ProductSpecification.hasColorIds(colorIds))
                .and(ProductSpecification.hasTitle(title))
                .and(ProductSpecification.priceBetween(minPrice, maxPrice));

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return productRepository.findAll(spec, pageable).map(productMapper::toListDto);
    }

    @Override
    public ProductDto detail(Long id) {
        log.info("Fetching product details for id: {}", id);
        ProductEntity entity = getProductEntity(id);
        return ProductMapper.INSTANCE.toDto(entity);
    }

    @Override
    public ProductDto detail(String slug) {
        log.info("Fetching product details for slug: {}", slug);
        ProductEntity entity = getProductEntity(slug);
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

        ProductCategoryEntity categoryEntity = categoryRepository.findById(productCreateDto.getCategoryID()).orElse(null);
        List<ProductColorEntity> colorEntities = colorRepository.findByIdIn(productCreateDto.getColorIDS());

        productEntity.setCategory(categoryEntity);
        productEntity.setColors(colorEntities);

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


        ProductEntity existingProduct = productRepository.findById(productUpdateDto.getId())
                .orElseThrow(() -> new RuntimeException("Product tapılmadı"));

        productMapper.updateEntityFromDto(productUpdateDto, existingProduct);

        existingProduct.setSlug(generateUniqueSlug(productUpdateDto.getTitle()));

        productRepository.save(existingProduct);

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

        String baseSlug = createSlug(input);
        String uniqueSlug = baseSlug;
        int counter = 1;

        while (productRepository.existsBySlug(uniqueSlug)) {
            uniqueSlug = baseSlug + "-" + counter;
            counter++;
        }

        return uniqueSlug;
    }

}

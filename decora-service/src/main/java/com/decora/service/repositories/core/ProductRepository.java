package com.decora.service.repositories.core;

import com.decora.service.models.core.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
    Optional<ProductEntity> findBySlug(String slug);
    boolean existsBySlug(String slug);
    List<ProductEntity> findByIdIn(List<Long> ids);
}

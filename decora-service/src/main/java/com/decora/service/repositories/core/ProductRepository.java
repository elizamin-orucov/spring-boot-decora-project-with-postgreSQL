package com.decora.service.repositories.core;

import com.decora.service.models.core.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findBySlug(String slug);
    boolean existsBySlug(String slug);
}

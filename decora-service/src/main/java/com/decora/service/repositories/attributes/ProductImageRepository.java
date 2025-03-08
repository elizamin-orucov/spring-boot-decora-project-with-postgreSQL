package com.decora.service.repositories.attributes;

import com.decora.service.models.core.product.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Long> {
}

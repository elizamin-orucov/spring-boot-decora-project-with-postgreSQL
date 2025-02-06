package com.decora.service.repositories.attributes;

import com.decora.service.models.attributes.ProductColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductColorRepository extends JpaRepository<ProductColorEntity, Long> {
}

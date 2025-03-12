package com.decora.service.repositories.attributes;

import com.decora.service.models.attributes.ProductColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductColorRepository extends JpaRepository<ProductColorEntity, Long> {
    List<ProductColorEntity> findByIdIn(List<Long> ids);
}

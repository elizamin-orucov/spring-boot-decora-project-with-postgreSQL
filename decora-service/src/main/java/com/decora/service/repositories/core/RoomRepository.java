package com.decora.service.repositories.core;

import com.decora.service.models.core.product.ProductEntity;
import com.decora.service.models.core.rooms.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    Optional<RoomEntity> findBySlug(String slug);
    boolean existsBySlug(String slug);
}


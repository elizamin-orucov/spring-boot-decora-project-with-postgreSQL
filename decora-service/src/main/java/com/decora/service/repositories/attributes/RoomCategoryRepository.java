package com.decora.service.repositories.attributes;

import com.decora.service.models.attributes.RoomCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomCategoryRepository extends JpaRepository<RoomCategoryEntity, Long> {
}

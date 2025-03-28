package com.decora.service.models.attributes;

import com.decora.service.base.BaseEntity;
import com.decora.service.models.core.rooms.RoomEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "room_category")
public class RoomCategoryEntity extends BaseEntity {

    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RoomEntity> rooms;
}

package com.decora.service.models.core.rooms;

import com.decora.service.base.BaseEntity;
import com.decora.service.models.attributes.RoomCategoryEntity;
import com.decora.service.models.core.product.ProductEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "room")
public class RoomEntity extends BaseEntity {

    @Column(name = "room_title", nullable = false)
    private String title;

    @Column(name = "room_slug", nullable = false, unique = true)
    private String slug;

    @Column(name = "room_description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private RoomCategoryEntity category;

    @ManyToMany(
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "room_products",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> products;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RoomImageEntity> images;
}

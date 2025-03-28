package com.decora.service.models.core.rooms;

import com.decora.service.base.BaseEntity;
import com.decora.service.models.core.product.ProductEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@Setter
//@Getter
//@Entity
//@Table(name = "room_products")
//public class RoomProductEntity extends BaseEntity {
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "room_id", nullable = false)
//    private RoomEntity room;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id", nullable = false)
//    private ProductEntity product;
//}
package com.decora.service.models.core.product;

import com.decora.service.base.BaseEntity;
import com.decora.service.models.attributes.ProductCategoryEntity;
import com.decora.service.models.attributes.ProductColorEntity;
import com.decora.service.models.enums.CollectionEnum;
import com.decora.service.models.enums.DiscountRateEnum;
import com.decora.service.models.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {
    @Column(name = "product_title", nullable = false)
    private String title;

    @Column(name = "product_description", nullable = false)
    private String description;

    @Column(name = "product_slug", unique = true, nullable = false, updatable = false)
    private String slug;

    @Column(name = "product_price", nullable = false)
    private Double price;

    @Column(name = "product_size", nullable = false)
    private String size;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategoryEntity category;

    @ManyToMany(
            cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
            }
    )
    @JoinTable(
            name = "product_color",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    private List<ProductColorEntity> colors;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductImageEntity> images;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_rate", nullable = false)
    private DiscountRateEnum discountRate;

    @Enumerated(EnumType.STRING)
    @Column(name = "collection", nullable = false)
    private CollectionEnum collection;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status", nullable = false)
    private ProductStatus status;

}

package com.decora.service.services.Impl;

import com.decora.service.models.attributes.ProductColorEntity;
import com.decora.service.models.core.product.ProductEntity;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecification {

    public static Specification<ProductEntity> hasCategory(Long categoryId) {
        return (root, query, cb) -> categoryId == null ? null : cb.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<ProductEntity> hasColorIds(List<Long> colorIds) {
        return (root, query, cb) -> {
            if (colorIds == null || colorIds.isEmpty()) return null;
            Join<ProductEntity, ProductColorEntity> colorsJoin = root.join("colors");
            return colorsJoin.get("id").in(colorIds);
        };
    }

    public static Specification<ProductEntity> hasTitle(String title) {
        return (root, query, cb) -> title == null ? null : cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<ProductEntity> priceBetween(Double minPrice, Double maxPrice) {
        return (root, query, cb) -> {
            if (minPrice == null && maxPrice == null) return null;
            if (minPrice == null) return cb.lessThanOrEqualTo(root.get("price"), maxPrice);
            if (maxPrice == null) return cb.greaterThanOrEqualTo(root.get("price"), minPrice);
            return cb.between(root.get("price"), minPrice, maxPrice);
        };
    }
}


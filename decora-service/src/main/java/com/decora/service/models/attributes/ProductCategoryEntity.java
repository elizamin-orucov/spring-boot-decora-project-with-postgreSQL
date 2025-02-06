package com.decora.service.models.attributes;

import com.decora.service.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "product_category")
public class ProductCategoryEntity extends BaseEntity {
    @Column(name = "category_name")
    private String categoryName;
}


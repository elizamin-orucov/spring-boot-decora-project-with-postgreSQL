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
@Table(name = "product_color")
public class ProductColorEntity extends BaseEntity {
    @Column(name = "color_name", nullable = false, unique = true)
    private String colorName;

    @Column(name = "color_code", nullable = false, unique = true)
    private String colorCode;
}

package com.decora.service.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ProductStatus {
    AVAILABLE,
    OUT_OF_STOCK,
    COMING_SOON,
    DISCONTINUED,
    PRE_ORDER;

    @JsonCreator
    public static ProductStatus fromString(String value) {
        return ProductStatus.valueOf(value.toUpperCase());
    }
}




package com.decora.service.models.enums;

public enum DiscountRateEnum {
    RATE_0(0),
    RATE_5(5),
    RATE_10(10),
    RATE_20(20),
    RATE_30(30);

    private final int percentage;

    DiscountRateEnum(int percentage) {
        this.percentage = percentage;
    }

    public int getPercentage() {
        return percentage;
    }
}


package com.decora.service.models.enums;

import java.util.ArrayList;
import java.util.List;

public enum CollectionEnum {
    ;

    private final int year;

    CollectionEnum(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public static List<Integer> getYears() {
        int currentYear = java.time.Year.now().getValue();
        List<Integer> years = new ArrayList<>();
        for (int i = 1950; i <= currentYear; i++) {
            years.add(i);
        }
        return years;
    }

    public static void main(String[] args) {
        System.out.println("Available years: " + getYears());
    }
}


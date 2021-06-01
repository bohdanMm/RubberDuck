package com.mykhalchuk.just_eat_it.domain.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum DailyDishType {

    BREAKFAST(new BigDecimal("0.25"), 1),
    DINNER(new BigDecimal("0.35"), 2),
    SNACK(new BigDecimal("0.15"), 3),
    SUPPER(new BigDecimal("0.25"), 4);

    private final BigDecimal percent;

    private final Integer order;

    DailyDishType(BigDecimal bigDecimal, Integer order) {
        this.percent = bigDecimal;
        this.order = order;
    }
}

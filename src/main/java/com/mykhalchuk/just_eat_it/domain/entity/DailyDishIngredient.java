package com.mykhalchuk.just_eat_it.domain.entity;

import com.mykhalchuk.just_eat_it.domain.enums.AmountType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyDishIngredient extends IdHolder {

    @ManyToOne
    private Ingredient ingredient;

    @ManyToOne
    private DailyDish dailyDish;

    private Integer amount;

    @Enumerated(EnumType.STRING)
    private AmountType amountType;

    private Integer calories;
}

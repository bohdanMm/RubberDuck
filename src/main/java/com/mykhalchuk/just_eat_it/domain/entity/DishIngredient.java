package com.mykhalchuk.just_eat_it.domain.entity;

import com.mykhalchuk.just_eat_it.domain.enums.AmountType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class DishIngredient extends IdHolder {

    @Enumerated(EnumType.STRING)
    private AmountType amountType;

    private Integer amount;

    @ManyToOne
    private Dish dish;

    @ManyToOne
    private Ingredient ingredient;
}

package com.mykhalchuk.just_eat_it.domain.entity;

import com.mykhalchuk.just_eat_it.domain.enums.AmountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Ingredient extends IdHolder {

    private String name;

    private Integer calories;

    @Enumerated(EnumType.STRING)
    private AmountType amountType;

    public Ingredient(Long id){
        this.id = id;
    }
}

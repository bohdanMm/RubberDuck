package com.mykhalchuk.just_eat_it.domain.entity;

import com.mykhalchuk.just_eat_it.domain.enums.Gender;
import com.mykhalchuk.just_eat_it.domain.enums.Role;
import com.mykhalchuk.just_eat_it.domain.enums.TrainingRate;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class MainUser extends IdHolder {

    @NotNull
    private String fullName;

    private Integer age;

    private Integer height;

    private Integer weight;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private Integer dailyCalories;

    private BigDecimal bodyMassIndex;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private TrainingRate trainingRate;
}

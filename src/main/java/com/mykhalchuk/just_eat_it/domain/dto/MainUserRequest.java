package com.mykhalchuk.just_eat_it.domain.dto;

import com.mykhalchuk.just_eat_it.domain.enums.Gender;
import com.mykhalchuk.just_eat_it.domain.enums.TrainingRate;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainUserRequest {

    @NotNull
    private String fullName;

    @NotNull
    private Integer age;

    @NotNull
    private Integer height;

    @NotNull
    private Integer weight;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Gender gender;

    @NotNull
    private TrainingRate trainingRate;
}

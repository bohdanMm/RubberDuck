package com.mykhalchuk.just_eat_it.domain.dto.dish;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    private String email;

    private String password;
}

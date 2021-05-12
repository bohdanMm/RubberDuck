package com.mykhalchuk.just_eat_it.domain.dto;

import com.mykhalchuk.just_eat_it.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private Long id;

    private String fullName;

    private Role role;
}

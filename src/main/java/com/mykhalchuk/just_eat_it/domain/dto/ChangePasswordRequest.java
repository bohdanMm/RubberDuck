package com.mykhalchuk.just_eat_it.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {

    private String oldPassword;

    private String newPassword;
}

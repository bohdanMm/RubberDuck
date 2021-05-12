package com.mykhalchuk.just_eat_it.controller;

import com.mykhalchuk.just_eat_it.domain.dto.LoginResponse;
import com.mykhalchuk.just_eat_it.domain.dto.MainUserDto;
import com.mykhalchuk.just_eat_it.domain.dto.dish.LoginDto;
import com.mykhalchuk.just_eat_it.service.MainUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/main-user")
public class MainUserController {

    private final MainUserService mainUserService;

    @PostMapping("register")
    public void save(@RequestBody MainUserDto mainUserDto) {
        System.out.println("test");
        mainUserService.register(mainUserDto);
    }

    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginDto loginDto) {
        return mainUserService.login(loginDto);
    }
}

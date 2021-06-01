package com.mykhalchuk.just_eat_it.controller;

import com.mykhalchuk.just_eat_it.domain.dto.ChangePasswordRequest;
import com.mykhalchuk.just_eat_it.domain.dto.LoginResponse;
import com.mykhalchuk.just_eat_it.domain.dto.MainUserDto;
import com.mykhalchuk.just_eat_it.domain.dto.MainUserRequest;
import com.mykhalchuk.just_eat_it.domain.dto.dish.LoginDto;
import com.mykhalchuk.just_eat_it.service.MainUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/main-user")
public class MainUserController {

    private final MainUserService mainUserService;

    @PostMapping("register")
    public void save(@RequestBody MainUserRequest mainUserRequest) {
        System.out.println("test");
        mainUserService.register(mainUserRequest);
    }

    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginDto loginDto) {
        return mainUserService.login(loginDto);
    }

    @GetMapping("{id}")
    public MainUserDto getById(@PathVariable Long id){
        return mainUserService.findDtoById(id);
    }

    @PutMapping("edit/{id}")
    public void editMainUser(@PathVariable Long id, @RequestBody MainUserRequest mainUserRequest){
        mainUserService.edit(id, mainUserRequest);
    }

    @PutMapping("edit/password/{id}")
    public void editPassword(@PathVariable Long id, @RequestBody ChangePasswordRequest changePasswordRequest){
        mainUserService.editPassword(id, changePasswordRequest);
    }
}

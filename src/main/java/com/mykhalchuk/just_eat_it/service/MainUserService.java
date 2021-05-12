package com.mykhalchuk.just_eat_it.service;

import com.mykhalchuk.just_eat_it.domain.dto.LoginResponse;
import com.mykhalchuk.just_eat_it.domain.dto.MainUserDto;
import com.mykhalchuk.just_eat_it.domain.dto.dish.LoginDto;
import com.mykhalchuk.just_eat_it.domain.entity.MainUser;
import com.mykhalchuk.just_eat_it.exception.BadRequestException;
import com.mykhalchuk.just_eat_it.mapper.MainUserMapper;
import com.mykhalchuk.just_eat_it.repository.MainUserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MainUserService {

    private final MainUserRepository mainUserRepository;
    private final MainUserMapper mainUserMapper;
    private final MenuService menuService;

    @Transactional
    public void register(MainUserDto mainUserDto) {
        if (mainUserRepository.existsByEmail(mainUserDto.getEmail())) {
            throw new BadRequestException("Користувач з такою електронною адресою вже існує");
        }
        MainUser mainUser = mainUserMapper.toEntity(mainUserDto);
        mainUserRepository.save(mainUser);
        menuService.createMenu(mainUser);
    }

    public LoginResponse login(LoginDto loginDto) {
        return mainUserRepository
                .findByEmailAndPassword(
                        loginDto.getEmail(),
                        new DigestUtils("SHA3-256").digestAsHex(loginDto.getPassword()))
                .map(mainUserMapper::toLoginResponse)
                .orElseThrow(() -> new BadRequestException("User with email: " + loginDto.getEmail() + " not found!"));
    }
}

package com.mykhalchuk.just_eat_it.service;

import com.mykhalchuk.just_eat_it.domain.dto.ChangePasswordRequest;
import com.mykhalchuk.just_eat_it.domain.dto.LoginResponse;
import com.mykhalchuk.just_eat_it.domain.dto.MainUserDto;
import com.mykhalchuk.just_eat_it.domain.dto.MainUserRequest;
import com.mykhalchuk.just_eat_it.domain.dto.dish.LoginDto;
import com.mykhalchuk.just_eat_it.domain.entity.MainUser;
import com.mykhalchuk.just_eat_it.exception.BadRequestException;
import com.mykhalchuk.just_eat_it.mapper.MainUserMapper;
import com.mykhalchuk.just_eat_it.repository.MainUserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainUserService {

    private final MainUserRepository mainUserRepository;
    private final MainUserMapper mainUserMapper;
    private final MenuService menuService;

    @Transactional
    public void register(MainUserRequest mainUserRequest) {
        if (mainUserRepository.existsByEmail(mainUserRequest.getEmail())) {
            throw new BadRequestException("Користувач з такою електронною адресою вже існує");
        }
        MainUser mainUser = mainUserMapper.toEntity(mainUserRequest);
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

    public Integer findDailyCaloriesByUserId(Long userId){
        return mainUserRepository.findCaloriesByUserId(userId);
    }

    public MainUserDto findDtoById(Long userId){
        return mainUserRepository.findById(userId)
                .map(mainUserMapper::toDto)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }

    public MainUser findById(Long userId){
        return mainUserRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }

    public void edit(Long id, MainUserRequest request){
        MainUser mainUser = findById(id);
        MainUser entity = mainUserMapper.toEntity(request);
        entity.setId(mainUser.getId());
        entity.setPassword(mainUser.getPassword());
        mainUserRepository.save(entity);
    }

    public void editPassword(Long id, ChangePasswordRequest request){
        MainUser mainUser = findById(id);
        if (!mainUser.getPassword().equals(new DigestUtils("SHA3-256").digestAsHex(request.getOldPassword()))){
            throw new BadRequestException("Not correct old password");
        }
        mainUser.setPassword(new DigestUtils("SHA3-256").digestAsHex(request.getNewPassword()));
        mainUserRepository.save(mainUser);
    }
}

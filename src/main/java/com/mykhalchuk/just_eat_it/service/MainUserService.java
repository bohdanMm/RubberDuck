package com.mykhalchuk.just_eat_it.service;

import com.mykhalchuk.just_eat_it.domain.dto.MainUserDto;
import com.mykhalchuk.just_eat_it.domain.entity.MainUser;
import com.mykhalchuk.just_eat_it.mapper.MainUserMapper;
import com.mykhalchuk.just_eat_it.repository.MainUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainUserService {

    private final MainUserRepository mainUserRepository;
    private final MainUserMapper mainUserMapper;

    public void save(MainUserDto mainUserDto){
        MainUser mainUser = mainUserMapper.toEntity(mainUserDto);
        mainUserRepository.save(mainUser);
    }
}

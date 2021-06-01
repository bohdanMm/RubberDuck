package com.mykhalchuk.just_eat_it.controller;

import com.mykhalchuk.just_eat_it.domain.dto.DailyMenuDto;
import com.mykhalchuk.just_eat_it.domain.dto.MenuDto;
import com.mykhalchuk.just_eat_it.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/menu")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("user/{id}")
    public MenuDto getById(@PathVariable Long id) {
        return menuService.getByUserId(id);
    }

    @GetMapping("/daily/user/{id}")
    public DailyMenuDto getById(@PathVariable Long id,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate menuDate) {
        return menuService.getDailyMenu(id, menuDate);
    }

    @PostMapping("substitute-dish")
    public void substituteDish(@RequestParam Long dailyDishId, @RequestParam Long newDishId){
        menuService.substituteDish(dailyDishId, newDishId);
    }
}

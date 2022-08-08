package pers.tanyong.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.tanyong.reggie.dto.SetmealDto;
import pers.tanyong.reggie.entity.Dish;
import pers.tanyong.reggie.entity.Setmeal;
import pers.tanyong.reggie.entity.SetmealDish;
import pers.tanyong.reggie.mapper.DishMapper;
import pers.tanyong.reggie.mapper.SetmealMapper;
import pers.tanyong.reggie.service.DishService;
import pers.tanyong.reggie.service.SetmealDishService;
import pers.tanyong.reggie.service.SetmealService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐的基本信息，操作setmeal，执行insert操作
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存套餐和菜品的关联信息，操作setmeal_dish,执行insert操作
        setmealDishService.saveBatch(setmealDishes);
    }
}

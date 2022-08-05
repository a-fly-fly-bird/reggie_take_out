package pers.tanyong.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.tanyong.reggie.entity.Dish;
import pers.tanyong.reggie.entity.Setmeal;
import pers.tanyong.reggie.mapper.DishMapper;
import pers.tanyong.reggie.mapper.SetmealMapper;
import pers.tanyong.reggie.service.DishService;
import pers.tanyong.reggie.service.SetmealService;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}

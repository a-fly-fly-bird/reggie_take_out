package pers.tanyong.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.tanyong.reggie.entity.SetmealDish;
import pers.tanyong.reggie.mapper.SetmealDishMapper;
import pers.tanyong.reggie.service.SetmealDishService;

@Service
@Slf4j
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {
}

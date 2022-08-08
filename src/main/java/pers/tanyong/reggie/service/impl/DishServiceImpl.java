package pers.tanyong.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.tanyong.reggie.dto.DishDto;
import pers.tanyong.reggie.entity.Dish;
import pers.tanyong.reggie.entity.DishFlavor;
import pers.tanyong.reggie.mapper.DishMapper;
import pers.tanyong.reggie.service.DishFlavorService;
import pers.tanyong.reggie.service.DishService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
// 要操作多张表，需要开启事务控制保持一致性
@Transactional
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品，同时保存口味数据
     * @param dishDto
     */
    @Override
    public void saveWithFlavor(DishDto dishDto) {
        // 保存菜品的基本信息，注意这里是dto，但是this只要dish
        this.save(dishDto);
        // 菜品ID，保证关系表间的联系
        Long id = dishDto.getId();

        List<DishFlavor> flavors = dishDto.getFlavors();

        // 函数式编程。Java8新特性，什么时候学一下
        flavors = flavors.stream().map((item) -> {
            item.setDishId(id);
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 查询Dish和Flavor
     * @param id
     * @return
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavors);
        return dishDto;
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        this.updateById(dishDto);
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(DishFlavor::getDishId, dishDto.getId());
        dishFlavorService.remove(queryWrapper);

        List<DishFlavor> flavors = dishDto.getFlavors();
        // 函数式编程。Java8新特性，什么时候学一下
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);
    }
}

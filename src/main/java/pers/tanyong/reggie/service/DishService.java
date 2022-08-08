package pers.tanyong.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.tanyong.reggie.dto.DishDto;
import pers.tanyong.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    // 需要同时操作dish表和dish_flavor表，所以需要自定义方法
    public void saveWithFlavor(DishDto dishDto);

    public DishDto getByIdWithFlavor(Long id);

    // 更新菜品信息和口味信息
    public void updateWithFlavor(DishDto dishDto);
}

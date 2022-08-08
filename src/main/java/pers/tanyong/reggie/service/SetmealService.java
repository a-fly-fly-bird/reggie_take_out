package pers.tanyong.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.tanyong.reggie.dto.SetmealDto;
import pers.tanyong.reggie.entity.Setmeal;

public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);
}

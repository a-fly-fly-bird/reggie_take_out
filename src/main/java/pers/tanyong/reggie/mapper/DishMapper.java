package pers.tanyong.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.tanyong.reggie.entity.Dish;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}

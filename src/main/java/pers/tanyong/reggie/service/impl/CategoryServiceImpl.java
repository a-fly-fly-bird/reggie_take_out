package pers.tanyong.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.tanyong.reggie.common.CustomException;
import pers.tanyong.reggie.entity.Category;
import pers.tanyong.reggie.entity.Dish;
import pers.tanyong.reggie.entity.Setmeal;
import pers.tanyong.reggie.mapper.CategoryMapper;
import pers.tanyong.reggie.service.CategoryService;
import pers.tanyong.reggie.service.DishService;
import pers.tanyong.reggie.service.SetmealService;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除，删除前判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        // 查询当前分类是否关联了菜品和套餐
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishLambdaQueryWrapper);

        // 如果已经关联
        if(count1 > 0){
            // 抛出异常
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);

        if(count2 > 0){
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }

        // 正常删除
        super.removeById(id);
    }
}

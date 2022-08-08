package pers.tanyong.reggie.dto;

import lombok.Data;
import pers.tanyong.reggie.entity.Setmeal;
import pers.tanyong.reggie.entity.SetmealDish;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}

package pers.tanyong.reggie.dto;

import lombok.Data;
import pers.tanyong.reggie.entity.Dish;
import pers.tanyong.reggie.entity.DishFlavor;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}

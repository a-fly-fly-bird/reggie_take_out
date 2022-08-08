package pers.tanyong.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.tanyong.reggie.entity.DishFlavor;
import pers.tanyong.reggie.mapper.DishFlavorMapper;
import pers.tanyong.reggie.service.DishFlavorService;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}

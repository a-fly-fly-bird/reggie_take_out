package pers.tanyong.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.tanyong.reggie.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}

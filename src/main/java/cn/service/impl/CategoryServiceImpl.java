package cn.service.impl;

import cn.entity.Category;
import cn.mapper.CategoryMapper;
import cn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/4/20
 * Time 16:08
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public Category findById(int id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Category> findList() {
        return categoryMapper.selectByExample(null);
    }
}

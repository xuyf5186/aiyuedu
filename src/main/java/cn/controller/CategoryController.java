package cn.controller;


import cn.entity.Category;
import cn.service.CategoryService;
import cn.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/3/19
 * Time 16:32
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value="/show/{id}",method = RequestMethod.GET)
    public ResultBean<Category> get(@PathVariable int id) throws Throwable {
        return new ResultBean<Category>(categoryService.findById(id));
    }
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public ResultBean<List<Category>> list() throws Throwable {
        return new ResultBean<List<Category>>(categoryService.findList());
    }
}

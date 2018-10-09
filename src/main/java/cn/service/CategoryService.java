package cn.service;

import cn.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/4/20
 * Time 15:40
 */
public interface CategoryService {
    Category findById(int id);
    List<Category> findList();
}

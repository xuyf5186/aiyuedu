package cn.service;


import cn.entity.Catalog;

import java.util.List;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/4/20
 * Time 17:20
 */
public interface CatalogService {
    Catalog findById(int id);
    List<Catalog> findListByBook(int bookId);
    void update(Catalog catalog);
    void insert(Catalog catalog);
    void delete(int id);
}

package cn.service;


import cn.entity.Book;

import java.util.List;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/4/20
 * Time 17:20
 */
public interface BookService {
    Book findById(int id);
    List<Book> findByName(String name);
    List<Book> findHotList();
    List<Book> findListByCategory(int categoryId);
    void add(Book book);
    Book readFiletoBook(String content);
    void update(Book book);
    void insert(Book book);
    void delete(int id);
}

package cn.controller;

import cn.entity.Book;
import cn.entity.Catalog;
import cn.entity.Category;
import cn.entity.Chapter;
import cn.service.BookService;
import cn.service.CategoryService;
import cn.service.TranslateService;
import cn.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
 * Date 2018/4/22
 * Time 12:55
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    TranslateService translateService;

    //推荐书籍
    @RequestMapping(value="/hot",method = RequestMethod.GET)
    public ResultBean<List<Book>> hot() throws Throwable {
        return new ResultBean<List<Book>>(bookService.findHotList());
    }
    //按ID查找
    @RequestMapping(value="/show/{id}",method = RequestMethod.GET)
    public ResultBean<Book> get(@PathVariable int id) throws Throwable {
        return new ResultBean<Book>(bookService.findById(id));
    }
    //搜索
    @RequestMapping(value="/search/{name}",method = RequestMethod.GET)
    public ResultBean<List<Book>> get(@PathVariable String name) throws Throwable {
        return new ResultBean<List<Book>>(bookService.findByName(name));
    }
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public ResultBean<List<Book>> list(Integer cid) throws Throwable {
        if(cid!=null && cid>0)
            return new ResultBean<List<Book>>(bookService.findListByCategory(cid));
        else
            throw new Throwable("不合格的参数cid");
    }
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public ResultBean<String> add(String content) throws Throwable {
        Book book=bookService.readFiletoBook(content);
        bookService.add(book);
        return new ResultBean<String>("添加成功");
    }
}
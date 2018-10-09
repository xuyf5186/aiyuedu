package cn.service.impl;

import cn.entity.*;
import cn.mapper.BookMapper;
import cn.mapper.CategoryMapper;
import cn.mapper.CommentMapper;
import cn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/4/20
 * Time 17:20
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    CatalogService catalogService;

    @Autowired
    ChapterService chapterService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentService commentService;
    @Override
    public Book findById(int id) {
        Book book=bookMapper.selectByPrimaryKey(id);
        book.setCategory(categoryService.findById(book.getCategoryId()));
        book.setComments(commentService.findListByBook(id,"updated_at"));
        return book;
    }

    @Override
    public List<Book> findByName(String name) {
        BookExample bookExample=new BookExample();
        bookExample.createCriteria().andNameLike('%'+name+'%');
        return bookMapper.selectByExample(bookExample);
    }

    @Override
    public List<Book> findHotList() {
        return bookMapper.selectHotList();
    }

    @Override
    public List<Book> findListByCategory(int categoryId) {
        BookExample bookExample=new BookExample();
        bookExample.createCriteria().andCategoryIdEqualTo(categoryId);
        return bookMapper.selectByExample(bookExample);
    }

    @Override
    public void update(Book book) {
        bookMapper.updateByPrimaryKeySelective(book);
    }

    @Transactional(value = "transactionManager")
    @Override
    public void add(Book book) {
        List<Catalog> catalogs=book.getCatalogs();
        insert(book);
        for (Catalog catalog: catalogs) {
            catalog.setBookId(book.getId());
            catalogService.insert(catalog);
            Chapter chapter=catalog.getChapter();
            if(chapter!=null) {
                chapter.setCatalogId(catalog.getId());
                chapterService.insert(chapter);
            }

        }

    }

    @Override
    public Book readFiletoBook(String content){
        int order=1;
        Book book=new Book();
        book.setCategoryId(2);
        book.setAuthor("南派三叔");
        book.setCover("3");
        book.setDescription("50年前由长沙土夫子（盗墓贼）出土的战国帛书，记载了一个奇特战国古墓的位置，50年后，其中一个土夫子的孙子在他的笔记中发现这个秘密，纠集了一批经验丰富的盗墓贼前去寻宝，谁也没有想到，这个古墓竟然有着这么多诡异的事情：七星疑棺，青眼狐尸，九头蛇柏。发现了这神秘的墓主人，并找到真正的棺椁。故事悬念重重，情节跌荡。");
        book.setTag("悬疑");
        List<Catalog> catalogs=new ArrayList<Catalog>();
        String [] juanStrs=content.split("第.卷");
        String bookName=juanStrs[0].substring(juanStrs[0].indexOf("《")+1,juanStrs[0].indexOf("》"));
        book.setName(bookName);
        juanStrs[0]="";
        for (String juanStr:
             juanStrs) {
            if(juanStr=="") continue;
            String [] chapterStrs=juanStr.split("第.章");
            int i=juanStr.indexOf("\r\n");
            String juanName=juanStr.substring(0,i).trim();
            Catalog juan=new Catalog(juanName,null,order++);
            catalogs.add(juan);
            chapterStrs[0]="";
            for (String chapterStr:chapterStrs)
            {
                if(chapterStr=="") continue;
                int j=chapterStr.indexOf("\r\n");
                String catalogName=chapterStr.substring(0,j).trim();
                Chapter chapter=new Chapter(chapterStr.substring(j+4));
                Catalog catalog=new Catalog(catalogName,chapter,order++);
                catalogs.add(catalog);
            }
        }
        book.setCatalogs(catalogs);
        return book;
    }
    @Override
    public void insert(Book book) {
        bookMapper.insertSelective(book);
    }

    @Override
    public void delete(int id) {

    }
}

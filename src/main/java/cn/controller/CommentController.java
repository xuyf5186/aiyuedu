package cn.controller;


import cn.annotation.UserAccess;
import cn.entity.Category;
import cn.entity.Comment;
import cn.service.CategoryService;
import cn.service.CommentService;
import cn.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping(value="/show/{id}",method = RequestMethod.GET)
    public ResultBean<Comment> get(@PathVariable int id) throws Throwable {
        return new ResultBean<Comment>(commentService.findById(id));
    }
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public ResultBean<List<Comment>> list(Integer bookId,Integer userId,String order) throws Throwable {
        if(bookId!=null)
            return new ResultBean<List<Comment>>(commentService.findListByBook(bookId,order));
        else if(userId!=null)
            return new ResultBean<List<Comment>>(commentService.findListByUser(userId,order));
        else return new ResultBean<List<Comment>>(new ArrayList<Comment>());
    }
    @UserAccess
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public ResultBean<String> add(HttpSession session,Comment comment) throws Throwable {
        int uid=(int)session.getAttribute("uid");
        commentService.insert(uid,comment);
        return new ResultBean<String>("插入成功");
    }
    @UserAccess
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public ResultBean<String> update(HttpSession session,Comment comment) throws Throwable {
        int uid=(int)session.getAttribute("uid");
        commentService.update(uid,comment);
        return new ResultBean<String>("修改成功");
    }
    @UserAccess
    @RequestMapping(value="/delete/{id}",method = RequestMethod.POST)
    public ResultBean<String> delete(HttpSession session,@PathVariable int id) throws Throwable {
        int uid=(int)session.getAttribute("uid");
        commentService.delete(uid,id);
        return new ResultBean<String>("删除成功");
    }
}

package cn.controller;

import cn.annotation.UserAccess;
import cn.entity.Book;
import cn.entity.Collection;
import cn.entity.User;
import cn.service.UserService;
import cn.util.ResultBean;
import cn.util.UploadImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/4/23
 * Time 15:46
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    //获取个人信息
    @RequestMapping(value="/show/{id}",method = RequestMethod.GET)
    public ResultBean<User> get(@PathVariable int id) throws Throwable {
        return new ResultBean<User>(userService.findById(id));
    }
    @UserAccess
    @RequestMapping(value="/info",method = RequestMethod.GET)
    public ResultBean<User> info(HttpSession session) throws Throwable {
        Integer uid=(Integer)session.getAttribute("uid");
        return new ResultBean<User>(userService.findById(uid));
    }
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResultBean<String> login(User user, HttpSession session) throws Throwable {
        Integer id=userService.login(user);
        session.setAttribute("uid",id);//保存登录态
        return new ResultBean<String>("登录成功");
    }
    @RequestMapping(value="/register",method = RequestMethod.POST)
    public ResultBean<String> register(User user) throws Throwable {
        userService.register(user);
        return new ResultBean<String>("注册成功");
    }
    //更新个人信息
    @UserAccess
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public ResultBean<String> update(HttpSession session,User user, UploadImageFile uploadImageFile) throws Throwable {
        if(user.getAccount()==null && user.getPassword()==null &&
                user.getCreatedAt()==null && user.getUpdatedAt()==null && user.getDeletedAt()==null) {
            user.setId((Integer)session.getAttribute("uid"));//session中取出用户id
            userService.update(user,session.getServletContext(),uploadImageFile.getImage());
            return new ResultBean<String>("修改成功");
        }
        else throw  new Throwable("存在不可修改的字段");
    }
    @UserAccess
    @RequestMapping(value="/update_password",method = RequestMethod.POST)
    public ResultBean<String> updatePassword(HttpSession session,String oldPassword,String newPassword) throws Throwable {

        Integer uid=(Integer)session.getAttribute("uid");//session中取出用户id
        userService.updatePassword(uid,oldPassword,newPassword);
        return new ResultBean<String>("修改成功");

    }
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ResultBean<String> logout(HttpSession session){
        session.removeAttribute("uid");
        return new ResultBean<String>("已注销");
    }
    @UserAccess
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public ResultBean<Integer> test(HttpSession session){
        return new ResultBean<Integer>((Integer) session.getAttribute("uid"));
    }
    //分享
    @UserAccess
    @RequestMapping(value = "/share",method = RequestMethod.POST)
    public ResultBean<String> share(HttpSession session) throws Throwable {
        Integer uid=(Integer) session.getAttribute("uid");
        userService.addShareCount(uid);
        return new ResultBean<String>("分享成功");
    }
    //收藏
    @UserAccess
    @RequestMapping(value = "/collect",method = RequestMethod.POST)
    public ResultBean<String> collect(HttpSession session,Integer bookId) throws Throwable {
        userService.collect((Integer) session.getAttribute("uid"), bookId);
        return new ResultBean<String>("收藏成功");
    }

    //取消收藏
    @UserAccess
    @RequestMapping(value = "/cancel_collect",method = RequestMethod.POST)
    public ResultBean<String> cancelCollect(HttpSession session,Integer bookId) throws Throwable {
        userService.cancelCollect((Integer) session.getAttribute("uid"), bookId);
        return new ResultBean<String>("取消收藏成功");
    }
    //收藏列表
    @UserAccess
    @RequestMapping(value = "/collections",method = RequestMethod.GET)
    public ResultBean<List<Book>> collections(HttpSession session) {
        Integer uid=(Integer)session.getAttribute("uid");
        return new ResultBean<List<Book>>(userService.getCollections(uid));
    }
}

package cn.controller;

import cn.annotation.UserAccess;
import cn.annotation.VIPAccess;
import cn.entity.Activity;
import cn.service.ActivityService;
import cn.util.ResultBean;
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
 * Date 2018/5/15
 * Time 10:45
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    ActivityService activityService;
    //按ID查找
    @RequestMapping(value="/show/{id}",method = RequestMethod.GET)
    public ResultBean<Activity> get(@PathVariable int id) throws Throwable {
        return new ResultBean<Activity>(activityService.findById(id));
    }
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public ResultBean<List<Activity>> list(HttpSession session,Integer uid) throws Throwable {
        if(uid==null)//没传uid 则直接拿登录的用户
            uid=(Integer)session.getAttribute("uid");
        return new ResultBean<List<Activity>>(activityService.findListByUid(uid));
    }
    @UserAccess
    @VIPAccess
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public ResultBean<String> add(HttpSession session,Activity activity) throws Throwable {
        Integer uid=(Integer)session.getAttribute("uid");
        activityService.insert(uid,activity);
        return new ResultBean<String>("添加成功");
    }
    @UserAccess
    @VIPAccess
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public ResultBean<String> update(HttpSession session,Activity activity) throws Throwable {
        Integer uid=(Integer)session.getAttribute("uid");
        activityService.update(uid,activity);
        return new ResultBean<String>("修改成功");
    }
    @UserAccess
    @VIPAccess
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public ResultBean<String> delete(HttpSession session,int id) throws Throwable {
        Integer uid=(Integer)session.getAttribute("uid");
        activityService.delete(uid,id);
        return new ResultBean<String>("删除成功");
    }
}

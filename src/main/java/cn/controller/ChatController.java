package cn.controller;

import cn.annotation.UserAccess;
import cn.entity.Book;
import cn.entity.FriendItem;
import cn.entity.Message;
import cn.service.BookService;
import cn.service.ChatService;
import cn.service.TranslateService;
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
 * Date 2018/4/22
 * Time 12:55
 */
@RestController
@RequestMapping("")
public class ChatController {

    @Autowired
    ChatService chatService;

    //获取用户的好友列表
    @UserAccess
    @RequestMapping(value="/friend/list",method = RequestMethod.GET)
    public ResultBean<List<FriendItem>> getFriends(HttpSession session) throws Throwable {
        Integer uid=(Integer)session.getAttribute("uid");
        return new ResultBean<List<FriendItem>>(chatService.getFriends(uid));
    }
    //添加好友
    @UserAccess
    @RequestMapping(value="/friend/add",method = RequestMethod.POST)
    public ResultBean<String> addFriends(HttpSession session,Integer uid) throws Throwable {
        Integer uid1=(Integer)session.getAttribute("uid");
        chatService.addFriend(uid1,uid);
        return new ResultBean<String>("添加成功");
    }
    //获取聊天记录
    @UserAccess
    @RequestMapping(value = "/message/list",method = RequestMethod.GET)
    public ResultBean<List<Message>> getMessages(HttpSession session,Integer friendId) throws Throwable {
        Integer uid=(Integer)session.getAttribute("uid");
        return new ResultBean<List<Message>>(chatService.getChatContent(uid,friendId));
    }
    //发送信息
    @UserAccess
    @RequestMapping(value = "/message/send",method = RequestMethod.POST)
    public ResultBean<String> sendMessage(HttpSession session,Integer friendId,String content,Integer type) throws Throwable {
        Integer uid=(Integer)session.getAttribute("uid");
        chatService.sendMessage(uid,friendId,content,type);
        return new ResultBean<String>("发送成功");
    }
}
package cn.service;

import cn.entity.Friend;
import cn.entity.FriendItem;
import cn.entity.Message;
import cn.entity.User;

import java.util.List;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/5/2
 * Time 13:39
 */
public interface ChatService {
    List<FriendItem> getFriends(int uid);
    void addFriend(int uid1,int uid2);
    List<Message> getChatContent(int fromUserId,int toUserId);
    void sendMessage(Integer uid,Integer fid,String content,Integer type) throws Throwable;
}

package cn.service.impl;

import cn.entity.*;
import cn.mapper.FriendMapper;
import cn.mapper.MessageMapper;
import cn.mapper.UserMapper;
import cn.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/5/2
 * Time 13:38
 */
@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    FriendMapper friendMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    MessageMapper messageMapper;
    @Override
    public List<FriendItem> getFriends(int uid) {
        return friendMapper.selectFriends(uid);
    }

    @Override
    public List<Message> getChatContent(int uid,int friendId) {
        Friend friend=friendMapper.selectByPrimaryKey(friendId);
        if(friend==null) return null;
        if(!friend.getUserOneId().equals(uid) && !friend.getUserTwoId().equals(uid))
            return null;
        MessageExample messageExample=new MessageExample();
        messageExample.createCriteria().andFriendIdEqualTo(friendId);
        messageExample.setOrderByClause("created_at DESC");
        return messageMapper.selectByExample(messageExample);
    }

    @Override
    public void addFriend(int uid1,int uid2) {
        Friend friend=new Friend();
        friend.setUserOneId(uid1);
        friend.setUserTwoId(uid2);
        friendMapper.insert(friend);
    }

    @Transactional
    @Override
    public void sendMessage(Integer uid, Integer fid, String content,Integer type) throws Throwable {
        if(fid==null) throw new Throwable("缺少必须参数fid");
        Friend friend=friendMapper.selectByPrimaryKey(fid);
        if(friend==null || (!friend.getUserOneId().equals(uid) && !friend.getUserTwoId().equals(uid)))
            throw new Throwable("不合理的fid");
        if(type==null) type=1;
        Message message=new Message();
        message.setType(type);
        message.setFromUser(uid);
        message.setFriendId(fid);
        message.setContent(content);
        messageMapper.insertSelective(message);
    }
}

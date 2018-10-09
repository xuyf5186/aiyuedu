package cn.service;

import cn.entity.Comment;

import java.util.List;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/4/20
 * Time 17:20
 */
public interface CommentService {
    Comment findById(int id);
    List<Comment> findListByBook(Integer bookId,String order);
    List<Comment> findListByUser(Integer userid,String order);
    void update(int uid,Comment comment);
    void insert(int uid,Comment comment);
    void delete(int uid,int id);
    float getScore(int id);
}

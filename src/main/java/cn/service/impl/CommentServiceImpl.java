package cn.service.impl;

import cn.entity.Book;
import cn.entity.Comment;
import cn.entity.CommentExample;
import cn.entity.User;
import cn.mapper.CommentMapper;
import cn.service.BookService;
import cn.service.CommentService;
import cn.service.UserService;
import com.sun.tools.doclets.internal.toolkit.util.CommentedMethodFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/4/20
 * Time 17:20
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Override
    public Comment findById(int id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Comment> findListByBook(Integer bookId, String order) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andBookIdEqualTo(bookId);
        List<Comment> comments = findList(commentExample, order);
        return comments;
    }

    private List<Comment> findList(CommentExample commentExample, String order) {
        if (order != null && !order.isEmpty()) {
            try {
                commentExample.setOrderByClause(order + " DESC");
                return commentMapper.selectByExampleWithBLOBs(commentExample);
            } catch (Throwable e) {
                commentExample.setOrderByClause("id ASC");
                return commentMapper.selectByExampleWithBLOBs(commentExample);
            }
        } else return commentMapper.selectByExampleWithBLOBs(commentExample);
    }

    @Override
    public List<Comment> findListByUser(Integer userId, String order) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andUserIdEqualTo(userId);
        List<Comment> comments = findList(commentExample, order);
        return comments;
    }

    @Override
    @Transactional
    public void update(int uid,Comment comment) {
        Comment oComment = commentMapper.selectByPrimaryKey(comment.getId());//找到此条评价对象
        if(oComment==null) throw new RuntimeException("评论已被删除");
        if(oComment.getUserId()!=uid) throw new RuntimeException("你没有权限操作此评论");
        commentMapper.updateByPrimaryKeySelective(comment);
        updateBook(comment.getBookId());
    }

    @Override
    @Transactional
    public void insert(int uid,Comment comment) {
        comment.setUserId(uid);
        CommentExample commentExample=new CommentExample();
        commentExample.createCriteria()
                .andUserIdEqualTo(uid)
                .andBookIdEqualTo(comment.getBookId())
                .andDeletedAtIsNull();
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if(comments.size()>0) throw new RuntimeException("不能重复评论同一本书");
        commentMapper.insertSelective(comment);
        updateBook(comment.getBookId());
    }

    @Override
    @Transactional
    public void delete(int uid,int id) {
            Comment comment = commentMapper.selectByPrimaryKey(id);//找到此条评价对象
            if(comment==null) throw new RuntimeException("评论不存在");
            if(comment.getUserId()!=uid) throw new RuntimeException("你没有权限操作此评论");
            comment.setDeletedAt(new Date());
            commentMapper.updateByPrimaryKey(comment);
            updateBook(comment.getBookId());
    }
    //更新对应书的评价分数
    private void updateBook(int bookId){
        float score=getScore(bookId);
        byte star= (byte) Math.floor(score/2);
        Book book=new Book();
        book.setId(bookId);
        book.setStar(star);
        book.setScore(score);
        bookService.update(book);
    }

    @Override
    public float getScore(int id) {
        int score=0;
        for (int i=1;i<=5;i++){
            int temp=commentMapper.getStarCountByBook(id,i);
            score+=2*i*temp;
        }
        int size=commentMapper.getCountByBook(id);
        BigDecimal bigDecimal1=new BigDecimal(score);
        BigDecimal bigDecimal2=new BigDecimal(size);
        BigDecimal bg = bigDecimal1.divide(bigDecimal2,1,BigDecimal.ROUND_HALF_UP);
        float result=bg.floatValue();
        return result;
    }
}

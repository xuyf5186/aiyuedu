package cn.service;

import cn.entity.Book;
import cn.entity.Collection;
import cn.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/4/20
 * Time 17:20
 */
public interface UserService {
    User findById(int id);
    List<User> findList();
    void addShareCount(int uid);
    Integer login(User user) throws Throwable;
    void register(User user) throws Throwable;
    void update(User user, ServletContext servletContext, MultipartFile image) throws IOException, NoSuchAlgorithmException;
    void updatePassword(Integer id,String oldPassword,String newPassword) throws Throwable;
    void collect(Integer uid,Integer bid) throws Throwable;
    void cancelCollect(Integer uid,Integer bid) throws Throwable;
    List<Book> getCollections(int uid);
}

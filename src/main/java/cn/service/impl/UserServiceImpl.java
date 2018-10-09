package cn.service.impl;

import cn.entity.*;
import cn.mapper.CollectionMapper;
import cn.mapper.UserMapper;
import cn.service.UserService;
import cn.util.UploadImageFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static String AVATARPATH="upload/avatar";
    @Autowired
    UserMapper userMapper;
    @Autowired
    CollectionMapper collectionMapper;
    @Override
    public User findById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public User findByAccount(String account) throws Throwable {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andAccountEqualTo(account);
        List<User> user=userMapper.selectByExample(userExample);
        if(user.isEmpty())
            throw new Throwable("账号不存在");
        return user.get(0);
    }
    @Override
    public List<User> findList() {
        return userMapper.selectByExample(null);
    }

    @Override
    public Integer login(User user) throws Throwable {
        User realUser = findByAccount(user.getAccount());
        if(null==realUser || !realUser.getPassword().equals(user.getPassword()))
        {
            throw new Throwable("密码错误");
        }
        return realUser.getId();
    }

    @Override
    public void register(User user) throws Throwable {
        //数据校验
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountEqualTo(user.getAccount());
        List<User> u = userMapper.selectByExample(userExample);
        user.setShareCount(0);
        if (u.isEmpty())
        {
            userMapper.insertSelective(user);
        }
        else
            throw new Throwable("账号已注册");
    }

    @Override
    public void update(User user, ServletContext servletContext, MultipartFile image) throws IOException, NoSuchAlgorithmException {
        if(image!=null && !image.isEmpty()) {
            String imgPath = servletContext.getRealPath(AVATARPATH);
            UploadImageFile.SaveImg(imgPath,image,user.getId());
            user.setAvatar(user.getId()+"");
        }
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public synchronized void addShareCount(int uid) {
        User user=userMapper.selectByPrimaryKey(uid);
        user.setShareCount(user.getShareCount()+1);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void updatePassword(Integer id,String oldPassword, String newPassword) throws Throwable {
        User user=userMapper.selectByPrimaryKey(id);
        if(user==null) throw new Throwable("用户不存在，请重新登录");
        if(!oldPassword.equals(user.getPassword())) throw new Throwable("原密码错误");
        //TODO:新密码验证合理性
        User user1=new User();
        user1.setId(user.getId());
        user1.setPassword(newPassword);
        userMapper.updateByPrimaryKeySelective(user1);
    }

    //收藏
    @Override
    public void collect(Integer uid, Integer bid) throws Throwable {
        if(bid==null) throw new Throwable("缺少bookId参数");
        CollectionExample collectionExample=new CollectionExample();
        collectionExample.createCriteria().andUserIdEqualTo(uid).andBookIdEqualTo(bid);
        List<Collection> collections=collectionMapper.selectByExample(collectionExample);
        if(collections.size()>0) throw new Throwable("您已收藏此书");
        Collection collection=new Collection();
        collection.setBookId(bid);
        collection.setUserId(uid);
        try {
            collectionMapper.insertSelective(collection);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            throw new Throwable("不合理的bookId");
        }
    }

    @Override
    public void cancelCollect(Integer uid, Integer bid) throws Throwable {
        if(bid==null) throw new Throwable("缺少bookId参数");
        CollectionExample collectionExample=new CollectionExample();
        collectionExample.createCriteria().andUserIdEqualTo(uid).andBookIdEqualTo(bid);
        List<Collection> collections=collectionMapper.selectByExample(collectionExample);
        if(collections.size()==0) throw new Throwable("您还未收藏此书");
        collectionMapper.deleteByPrimaryKey(collections.get(0).getId());
    }

    @Override
    public List<Book> getCollections(int uid) {
        return collectionMapper.getCollections(uid);
    }

}

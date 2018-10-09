package cn.service;

import cn.entity.Activity;

import java.util.List;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/5/15
 * Time 10:32
 */
public interface ActivityService {
    Activity findById(int id);
    List<Activity> findListByUid(int uid);
    void update(Integer uid,Activity activity);
    void insert(Integer uid,Activity activity);
    void delete(Integer uid,int id);
}

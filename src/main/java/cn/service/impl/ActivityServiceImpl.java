package cn.service.impl;

import cn.entity.Activity;
import cn.entity.ActivityExample;
import cn.mapper.ActivityMapper;
import cn.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/5/15
 * Time 10:32
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;
    @Override
    public Activity findById(int id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Activity> findListByUid(int uid) {
        ActivityExample activityExample=new ActivityExample();
        activityExample.createCriteria().andFromUserEqualTo(uid);
        activityExample.setOrderByClause("updated_at DESC");
        return activityMapper.selectByExample(activityExample);
    }

    @Override
    public void update(Integer uid,Activity activity) {
        Activity oActivity=activityMapper.selectByPrimaryKey(activity.getId());
        if(oActivity==null) throw new RuntimeException("活动不存在");
        if(!Objects.equals(oActivity.getFromUser(), uid)) throw new RuntimeException("你没有权限操作此活动");
        activityMapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    public void insert(Integer uid,Activity activity) {

        activity.setFromUser(uid);
        activityMapper.insertSelective(activity);
    }

    @Override
    public void delete(Integer uid,int id) {
        Activity oActivity=activityMapper.selectByPrimaryKey(id);
        if(oActivity==null) throw new RuntimeException("活动不存在");
        if(!Objects.equals(oActivity.getFromUser(), uid)) throw new RuntimeException("你没有权限操作此活动");
        activityMapper.deleteByPrimaryKey(id);
    }
}

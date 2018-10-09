package cn.service.impl;

import cn.entity.Chapter;
import cn.entity.ChapterExample;
import cn.mapper.ChapterMapper;
import cn.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/4/20
 * Time 17:20
 */
@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    ChapterMapper chapterMapper;
    @Override
    public Chapter findById(int id) {
        return chapterMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Chapter> findListByCatalog(int catalogId) {
        ChapterExample chapterExample = new ChapterExample();
        chapterExample.createCriteria().andCatalogIdEqualTo(catalogId);
        return chapterMapper.selectByExample(chapterExample);
    }

    @Override
    public void update(Chapter chapter) {

    }

    @Override
    public void updateBatch(List<Chapter> chapters) {

    }

    @Override
    public void insert(Chapter chapter) {
        chapterMapper.insertSelective(chapter);
    }

    @Override
    public void insertBatch(List<Chapter> chapters) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public String translate(String english) {
        return null;
    }
}

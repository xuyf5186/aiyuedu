package cn.service;

import cn.entity.Chapter;

import java.util.List;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/4/20
 * Time 17:20
 */
public interface ChapterService {
    Chapter findById(int id);
    List<Chapter> findListByCatalog(int catalogId);
    void update(Chapter chapter);
    void updateBatch(List<Chapter> chapters);
    void insert(Chapter chapter);
    void insertBatch(List<Chapter> chapters);
    void delete(int id);
    String translate(String english);
}

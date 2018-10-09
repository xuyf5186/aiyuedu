package cn.service.impl;

import cn.entity.Catalog;
import cn.entity.CatalogExample;
import cn.entity.Chapter;
import cn.mapper.CatalogMapper;
import cn.service.CatalogService;
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
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    private CatalogMapper catalogMapper;

    @Autowired
    private ChapterService chapterService;

    @Override
    public Catalog findById(int id) {
        return catalogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Catalog> findListByBook(int bookId) {
        CatalogExample catalogExample=new CatalogExample();
        catalogExample.createCriteria().andBookIdEqualTo(bookId);
        return catalogMapper.selectByExample(catalogExample);
    }

    @Override
    public void update(Catalog catalog) {
        catalogMapper.updateByPrimaryKeySelective(catalog);
    }

    @Override
    public void insert(Catalog catalog) {
        catalogMapper.insertSelective(catalog);
    }

    @Override
    public void delete(int id) {

    }
}

package cn.controller;


import cn.entity.Catalog;
import cn.entity.Chapter;
import cn.service.CatalogService;
import cn.service.ChapterService;
import cn.service.TranslateService;
import cn.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/3/19
 * Time 16:32
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    ChapterService chapterService;
    @Autowired
    TranslateService translateService;

    @RequestMapping(value="/show/{id}",method = RequestMethod.GET)
    public ResultBean<Chapter> get(@PathVariable int id) throws Throwable {
        return new ResultBean<Chapter>(chapterService.findById(id));
    }
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public ResultBean<List<Chapter>> list(int cid) throws Throwable {
        return new ResultBean<List<Chapter>>(chapterService.findListByCatalog(cid));
    }
    @RequestMapping(value="/translate/{word}",method = RequestMethod.GET)
    public ResultBean<String> list(@PathVariable String word) throws Throwable {
        return new ResultBean<String>(translateService.enToCh(word));
    }
}

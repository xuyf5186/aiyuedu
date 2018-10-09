package cn.controller;


import cn.entity.Catalog;
import cn.service.CatalogService;
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
@RequestMapping("/catalog")
public class CatalogController {
    @Autowired
    CatalogService catalogService;

    @RequestMapping(value="/show/{id}",method = RequestMethod.GET)
    public ResultBean<Catalog> get(@PathVariable int id) throws Throwable {
        return new ResultBean<Catalog>(catalogService.findById(id));
    }
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public ResultBean<List<Catalog>> list(int bid) throws Throwable {
        return new ResultBean<List<Catalog>>(catalogService.findListByBook(bid));
    }
}

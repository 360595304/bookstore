package com.hu.bookstore.controller;

import com.hu.bookstore.entity.Goods;
import com.hu.bookstore.entity.SysUser;
import com.hu.bookstore.entity.Trolley;
import com.hu.bookstore.response.Result;
import com.hu.bookstore.service.GoodsService;
import com.hu.bookstore.service.SysUserService;
import com.hu.bookstore.service.TrolleyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author suhu
 * @createDate 2021-11-05
 */
@RestController
@RequestMapping("/goods")
@CrossOrigin
@Api(value = "购物车管理模块", tags = "购物车接口")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private TrolleyService trolleyService;

    @PostMapping("/addGoods")
    public Result addGoods(@RequestBody Goods goods) {
        SysUser userInfo = sysUserService.getUserInfo();
        Long id = userInfo.getId();
        Trolley trolley = trolleyService.getByUserId(id);
        goods.setTrolleyId(trolley.getId());
        goodsService.addGoods(goods);
        return Result.ok();
    }

    @PostMapping("/updateGoods")
    public Result updateGoods(@RequestBody Goods goods) {
        goodsService.updateGoods(goods);
        return Result.ok();
    }
}

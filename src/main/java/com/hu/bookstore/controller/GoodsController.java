package com.hu.bookstore.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hu.bookstore.entity.Book;
import com.hu.bookstore.entity.Goods;
import com.hu.bookstore.entity.SysUser;
import com.hu.bookstore.entity.Trolley;
import com.hu.bookstore.response.Result;
import com.hu.bookstore.service.BookService;
import com.hu.bookstore.service.GoodsService;
import com.hu.bookstore.service.SysUserService;
import com.hu.bookstore.service.TrolleyService;
import com.hu.bookstore.vo.GoodsVO;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private BookService bookService;

    // 传书id
    @PostMapping("/addGoods")
    public Result addGoods(@Param("bookId") String bookId) {
        Goods goods = new Goods();
        goods.setBookId(bookId);
        goods.setCount(1);
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

    @GetMapping("/get")
    public Result getGoods() {
        Long userId = sysUserService.getUserInfo().getId();
        Trolley trolley = trolleyService.getByUserId(userId);
        List<GoodsVO> list = bookService.getByCartId(trolley.getId());

        return Result.ok().data("list", list);
    }

    @PostMapping("/setChecked")
    public Result setChecked(@Param("checked") Boolean checked, @Param("id") String id) {
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        int goodsId = Integer.parseInt(id);
        wrapper.set("checked", checked).eq("id", goodsId);
        goodsService.update(wrapper);
        return Result.ok();
    }

    @PostMapping("/setNumber")
    public Result setNumber(@Param("number") Integer number, @Param("id") String id) {
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        int goodsId = Integer.parseInt(id);
        wrapper.set("count", number).eq("id", goodsId);
        goodsService.update(wrapper);
        return Result.ok();
    }

    @PostMapping("/remove")
    public Result removeGoods(@Param("id")Integer id) {
        goodsService.removeById(id);
        return Result.ok();
    }
}

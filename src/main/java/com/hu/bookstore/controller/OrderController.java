package com.hu.bookstore.controller;

import com.hu.bookstore.entity.Goods;
import com.hu.bookstore.entity.Order;
import com.hu.bookstore.entity.SysUser;
import com.hu.bookstore.response.Result;
import com.hu.bookstore.service.GoodsService;
import com.hu.bookstore.service.OrderService;
import com.hu.bookstore.service.SysUserService;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author suhu
 * @createDate 2021-11-05
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
@Api(value = "订单管理模块", tags = "订单接口")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/create")
    public Result pay(@RequestBody Order order, @Param("goodsId") int goodsId) {
        SysUser userInfo = sysUserService.getUserInfo();
        Long id = userInfo.getId();
        order.setUserId(id);
        orderService.addOrder(order);
        goodsService.setOrderId(goodsId, order.getId());
        return Result.ok();
    }


    @PostMapping("/createByTrolley/{trolleyId}")
    public Result payByTrolley(@RequestBody Order order, @PathVariable("trolleyId") int trolleyId) {
        SysUser userInfo = sysUserService.getUserInfo();
        Long id = userInfo.getId();
        order.setUserId(id);
        orderService.addOrder(order);
        goodsService.setAllOrderId(trolleyId, order.getId());
        return Result.ok();
    }
}

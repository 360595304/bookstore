package com.hu.bookstore.controller;

import com.hu.bookstore.entity.*;
import com.hu.bookstore.response.Result;
import com.hu.bookstore.service.*;
import com.hu.bookstore.vo.OrderVO;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    TrolleyService trolleyService;

    @Autowired
    BookService bookService;

    // 传收货信息和货物id
    @PostMapping("/create")
    public Result pay(@RequestBody Order order, @Param("goodsId") int goodsId) {
        SysUser userInfo = sysUserService.getUserInfo();
        Long id = userInfo.getId();
        order.setUserId(id);
        orderService.addOrder(order);
        goodsService.setOrderId(goodsId, order.getId());
        return Result.ok();
    }

    // 传收货信息
    @PostMapping("/createByTrolley")
    public Result payByTrolley(@RequestBody Order order) {
        SysUser userInfo = sysUserService.getUserInfo();
        Long id = userInfo.getId();
        Trolley trolley = trolleyService.getByUserId(id);
        order.setUserId(id);
        String trolleyId = trolley.getId();
        orderService.addOrder(order);
        goodsService.setAllOrderId(trolleyId, order.getId());
        return Result.ok();
    }

    // 获取当前用户的订单
    @GetMapping("/get")
    public Result getOrder() {
        SysUser userInfo = sysUserService.getUserInfo();
        List<OrderVO> orderVOList = new ArrayList<>();
        List<Order> orderList = orderService.getByUser(userInfo.getId());
        for (Order order : orderList) {
            List<Goods> goodsList = goodsService.getOrderGoods(order.getId());
            OrderVO orderVO = orderService.transformOrderVO(order, userInfo, goodsList);
            orderVOList.add(orderVO);
        }

        return Result.ok().data("order", orderVOList);
    }


    @PostMapping("/finish/{orderId}")
    public Result finishOrder(@PathVariable("orderId") String orderId) {
        orderService.finish(orderId);
        for (Book book : bookService.getOrderBook(orderId)) {
            bookService.addSales(book.getId());
        }

        return Result.ok();
    }


//    @PostMapping("/finish")
//    public Result finishOrder(@RequestBody Order order) {
//        orderService.updateById(order);
//        return Result.ok();
//    }

    @GetMapping("/getOrderList")
    public Result getOrderList(@RequestParam(name = "current", defaultValue = "1") int current,
                               @RequestParam(name = "size", defaultValue = "8") int size,
                               @RequestParam(name = "key", defaultValue = "") String keyword) {
        List<Order> allOrder;
        int total;
        if ("".equals(keyword)) {
            allOrder = orderService.getAllOrder(current, size);
            total = orderService.getTotal(current, size);
        } else {
            allOrder = orderService.getAllOrderByKeyword(current, size, keyword);
            total = orderService.getTotalByKeyword(current, size, keyword);
        }
        List<OrderVO> orderVOList = new ArrayList<>();
        for (Order order : allOrder) {
            List<Goods> goodsList = goodsService.getOrderGoods(order.getId());
            SysUser user = sysUserService.getById(order.getUserId());
            OrderVO orderVO = orderService.transformOrderVO(order, user, goodsList);
            orderVOList.add(orderVO);
        }
        return Result.ok().data("orderList", orderVOList).data("total", total);
    }

    @DeleteMapping("delete/{orderId}")
    public Result deleteOrder(@PathVariable("orderId") String orderId) {
        Map<String, Object> map = new HashMap<>();
        map.put("order_id", orderId);
        goodsService.removeByMap(map);
        orderService.removeById(orderId);
        return Result.ok();
    }

    @PostMapping("/updateOrder")
    public Result updateOrder(@RequestBody Order order) {
        orderService.updateMsg(order);
        return Result.ok();
    }
}

package com.hu.bookstore.controller;

import com.baomidou.mybatisplus.extension.api.R;
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
    private TrolleyService trolleyService;

    @Autowired
    private BookService bookService;

    // 传收货信息和书id
    @PostMapping("/create")
    public Result create(@Param("recName") String recName, @Param("recPhone") String recPhone,
                         @Param("recAddress") String recAddress, @Param("notes") String notes,
                         @Param("bookId") String bookId) {
        SysUser userInfo = sysUserService.getUserInfo();
        Long id = userInfo.getId();
        Order order = new Order();
        order.setUserId(id);
        order.setRecName(recName);
        order.setRecPhone(recPhone);
        order.setRecAddress(recAddress);
        order.setNotes(notes);
        orderService.addOrder(order);
        Goods goods = new Goods();
        goods.setBookId(bookId);
        goods.setOrderId(order.getId());
        goodsService.addGoods(goods);
        return Result.ok();
    }

    // 传收货信息
    @PostMapping("/createByTrolley")
    public Result createByTrolley(@Param("recName") String recName, @Param("recPhone") String recPhone,
                                  @Param("recAddress") String recAddress, @Param("notes") String notes) {
        Order order = new Order();
        order.setRecName(recName);
        order.setRecPhone(recPhone);
        order.setRecAddress(recAddress);
        order.setNotes(notes);
        SysUser userInfo = sysUserService.getUserInfo();
        Long id = userInfo.getId();
        Trolley trolley = trolleyService.getByUserId(id);
        order.setUserId(id);
        String trolleyId = trolley.getId();
        orderService.addOrder(order);
        goodsService.setAllOrderId(trolleyId, order.getId());
        System.out.println(order);
        return Result.ok().data("orderId", order.getId());
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


    @GetMapping("getById/{id}")
    public Result getById(@PathVariable("id") String orderId) {
        Order order = orderService.getById(orderId);
        return Result.ok().data("order", order);
    }

    @PostMapping("/finish/{orderId}")
    public Result finishOrder(@PathVariable("orderId") String orderId) {
        orderService.finish(orderId);
        for (Book book : bookService.getOrderBook(orderId)) {
            bookService.addSales(book.getId());
        }

        return Result.ok().message("订单已完成，感谢您的购买！");
    }

    @PostMapping("/payOrder/{orderId}")
    public Result payOrder(@PathVariable("orderId") String orderId) {
        orderService.pay(orderId);
        return Result.ok().message("支付成功！");
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
        System.out.println(order);
        orderService.updateMsg(order);
        return Result.ok();
    }

    @GetMapping("getOrderPrice/{orderId}")
    public Result getOrderPrice(@PathVariable("orderId") String orderId) {
        double price = orderService.getOrderPrice(orderId);
        int p = (int) (price * 100);
        price = p * 1.0 / 100;
        return Result.ok().data("price", price);
    }
}

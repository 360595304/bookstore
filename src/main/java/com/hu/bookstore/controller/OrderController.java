package com.hu.bookstore.controller;

import com.hu.bookstore.response.Result;
import com.hu.bookstore.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suhu
 * @createDate 2021-11-05
 */
@RestController
@RequestMapping("/goods")
@CrossOrigin
@Api(value = "订单管理模块", tags = "订单接口")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public Result pay() {

        return Result.ok();
    }

}

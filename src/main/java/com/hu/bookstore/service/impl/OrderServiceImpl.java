package com.hu.bookstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.bookstore.entity.Order;
import com.hu.bookstore.mapper.OrderMapper;
import com.hu.bookstore.mapper.SysUserMapper;
import com.hu.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author suhu
 * @createDate 2021-10-31
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    SysUserMapper userMapper;


    @Override
    public void addOrder(Order order) {
        orderMapper.insert(order);
    }
}

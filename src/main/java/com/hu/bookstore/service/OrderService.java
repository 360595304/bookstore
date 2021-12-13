package com.hu.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.bookstore.entity.Goods;
import com.hu.bookstore.entity.Order;
import com.hu.bookstore.entity.SysUser;
import com.hu.bookstore.vo.OrderVO;

import java.util.List;

public interface OrderService extends IService<Order> {
    void addOrder(Order order);

    List<Order> getByUser(Long userId);

    void finish(String orderId);

    List<Order> getAllOrder(int current, int size);

    List<Order> getAllOrderByKeyword(int current, int size, String keyword);

    OrderVO transformOrderVO(Order order, SysUser user, List<Goods> goodsList);

    int getTotalByKeyword(int current, int size, String keyword);

    int getTotal(int current, int size);

    void updateMsg(Order order);

    void pay(String orderId);

    double getOrderPrice(String orderId);
}

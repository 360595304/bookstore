package com.hu.bookstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.bookstore.entity.Goods;
import com.hu.bookstore.entity.Order;
import com.hu.bookstore.entity.SysUser;
import com.hu.bookstore.mapper.OrderMapper;
import com.hu.bookstore.mapper.SysUserMapper;
import com.hu.bookstore.service.GoodsService;
import com.hu.bookstore.service.OrderService;
import com.hu.bookstore.vo.GoodsVO;
import com.hu.bookstore.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    GoodsService goodsService;

    @Override
    public void addOrder(Order order) {
        orderMapper.insert(order);
    }

    @Override
    public List<Order> getByUser(Long userId) {
        return orderMapper.getUserOrder(userId);
    }

    @Override
    public void finish(String orderId) {
        orderMapper.updateState(orderId);
    }

    @Override
    public List<Order> getAllOrder(int current, int size) {
        return orderMapper.getAllOrder((current-1)*size, size);
    }

    @Override
    public List<Order> getAllOrderByKeyword(int current, int size, String keyword) {
        return orderMapper.getAllOrderBykeyword((current-1)*size, size, keyword);
    }

    @Override
    public OrderVO transformOrderVO(Order order, SysUser user, List<Goods> goodsList) {
        OrderVO orderVO = new OrderVO();
        orderVO.setState(order.getState());
        orderVO.setNickname(user.getNickname());
        List<GoodsVO> goodsVOList = new ArrayList<>();
        for (Goods goods : goodsList) {
            goodsVOList.add(goodsService.transformGoodsVO(goods));
        }
        orderVO.setGoodsList(goodsVOList);
        orderVO.setNotes(order.getNotes());
        orderVO.setId(order.getId());
        orderVO.setCreateTime(order.getCreateTime());
        orderVO.setRecAddress(order.getRecAddress());
        orderVO.setRecName(order.getRecName());
        orderVO.setRecPhone(order.getRecPhone());
        return orderVO;
    }

    @Override
    public int getTotalByKeyword(int current, int size, String keyword) {
        return orderMapper.getTotalByKeyword(current, size, keyword);
    }

    @Override
    public int getTotal(int current, int size) {
        return orderMapper.getTotal(current, size);
    }

    @Override
    public void updateMsg(Order order) {
        orderMapper.updateMsg(order);
    }

    @Override
    public void pay(String orderId) {
        orderMapper.payOrder(orderId);
    }
}

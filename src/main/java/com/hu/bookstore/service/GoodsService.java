package com.hu.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.bookstore.entity.Goods;
import com.hu.bookstore.entity.Order;

public interface GoodsService extends IService<Goods> {
    void addGoods(Goods goods);

    void updateGoods(Goods goods);

    void setOrderId(int goodsId, String orderId);

    void setAllOrderId(int trolleyId, String orderId);
}

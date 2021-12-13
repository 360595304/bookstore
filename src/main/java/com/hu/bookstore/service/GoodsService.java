package com.hu.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.bookstore.entity.Goods;
import com.hu.bookstore.vo.GoodsVO;

import java.util.List;

public interface GoodsService extends IService<Goods> {
    void addGoods(Goods goods);

    void updateGoods(Goods goods);

    void setOrderId(String goodsId, String orderId);

    void setAllOrderId(String trolleyId, String orderId);

    List<Goods> getOrderGoods(String id);

    public GoodsVO transformGoodsVO(Goods goods);

}

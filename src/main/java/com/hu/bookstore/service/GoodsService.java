package com.hu.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.bookstore.entity.Goods;

public interface GoodsService extends IService<Goods> {
    void addGoods(Goods goods);
}

package com.hu.bookstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.bookstore.entity.Goods;
import com.hu.bookstore.mapper.GoodsMapper;
import com.hu.bookstore.mapper.TrolleyMapper;
import com.hu.bookstore.service.GoodsService;
import com.hu.bookstore.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author suhu
 * @createDate 2021-11-05
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Autowired
    private TrolleyMapper trolleyMapper;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void addGoods(Goods goods) {
        goodsMapper.insert(goods);
    }
}

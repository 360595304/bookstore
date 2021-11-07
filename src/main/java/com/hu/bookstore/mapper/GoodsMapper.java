package com.hu.bookstore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.bookstore.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    void setOrderId(@Param("goodsId") int goodsId, @Param("orderId") String orderId);

    void setAllOrderId(@Param("trolleyId") int trolleyId, @Param("orderId") String orderId);
}

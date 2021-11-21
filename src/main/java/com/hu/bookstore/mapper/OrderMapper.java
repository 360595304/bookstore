package com.hu.bookstore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.bookstore.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> getUserOrder(@Param("userId") Long userId);

    void updateState(@Param("orderId") String orderId);

    List<Order> getAllOrder(@Param("begin")int begin, @Param("size")int size);

    List<Order> getAllOrderBykeyword(@Param("begin")int begin, @Param("size")int size, @Param("keyword") String keyword);

    int getTotalByKeyword(@Param("begin")int begin, @Param("size")int size, @Param("keyword") String keyword);

    int getTotal(@Param("begin")int begin, @Param("size")int size);

    void updateMsg(Order order);

    void payOrder(@Param("orderId")String orderId);
}

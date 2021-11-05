package com.hu.bookstore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.bookstore.entity.Trolley;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrolleyMapper extends BaseMapper<Trolley> {

    Trolley getByUserId(Long id);
}

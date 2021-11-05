package com.hu.bookstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.bookstore.entity.Trolley;
import com.hu.bookstore.mapper.TrolleyMapper;
import com.hu.bookstore.service.TrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author suhu
 * @createDate 2021-11-05
 */
@Service
public class TrolleyServiceImpl extends ServiceImpl<TrolleyMapper, Trolley> implements TrolleyService {
    @Autowired
    TrolleyMapper trolleyMapper;

    @Override
    public Trolley getByUserId(Long id) {
        return trolleyMapper.getByUserId(id);
    }
}

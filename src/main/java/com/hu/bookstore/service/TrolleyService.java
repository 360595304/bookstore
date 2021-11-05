package com.hu.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.bookstore.entity.Trolley;

public interface TrolleyService extends IService<Trolley> {
    Trolley getByUserId(Long id);
}

package com.hu.bookstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.bookstore.entity.BrowseHistory;
import com.hu.bookstore.mapper.BookMapper;
import com.hu.bookstore.mapper.BrowseHistoryMapper;
import com.hu.bookstore.mapper.SysUserMapper;
import com.hu.bookstore.service.BrowseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author suhu
 * @createDate 2021-10-31
 */
@Service
public class BrowseHistoryServiceImpl extends ServiceImpl<BrowseHistoryMapper, BrowseHistory> implements BrowseHistoryService {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    SysUserMapper userMapper;

    @Autowired
    BrowseHistoryMapper browseHistoryMapper;
}

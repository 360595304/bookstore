package com.hu.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.bookstore.entity.Book;
import com.hu.bookstore.entity.BrowseHistory;
import com.hu.bookstore.vo.HistoryVO;

import java.util.List;

public interface BrowseHistoryService extends IService<BrowseHistory> {
    void add(BrowseHistory history);

    List<HistoryVO> getHistory(Long userId);
}

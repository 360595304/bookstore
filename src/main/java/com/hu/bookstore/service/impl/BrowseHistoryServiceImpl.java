package com.hu.bookstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.bookstore.entity.Book;
import com.hu.bookstore.entity.BrowseHistory;
import com.hu.bookstore.mapper.BookMapper;
import com.hu.bookstore.mapper.BrowseHistoryMapper;
import com.hu.bookstore.mapper.SysUserMapper;
import com.hu.bookstore.service.BrowseHistoryService;
import com.hu.bookstore.service.SysUserService;
import com.hu.bookstore.vo.HistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Autowired
    SysUserService userService;

    @Override
    public void add(BrowseHistory history) {
        String bookId = history.getBookId();
        List<BrowseHistory> query = browseHistoryMapper.query(bookId, userService.getUserInfo().getId());
        if (query == null || query.size() == 0)
            browseHistoryMapper.insert(history);
        else {
            BrowseHistory item = query.get(0);
            item.setBrowseTime(new Date());
            browseHistoryMapper.updateById(item);
        }
    }

    @Override
    public List<HistoryVO> getHistory(Long userId) {
        return browseHistoryMapper.getHistoryBook(userId);
    }
}

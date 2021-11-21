package com.hu.bookstore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.bookstore.entity.Book;
import com.hu.bookstore.entity.BrowseHistory;
import com.hu.bookstore.vo.HistoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BrowseHistoryMapper extends BaseMapper<BrowseHistory> {

    List<HistoryVO> getHistoryBook(@Param("userId") Long userId);

    List<BrowseHistory> query(@Param("bookId")String bookId, @Param("userId")Long userId);
}

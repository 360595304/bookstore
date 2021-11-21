package com.hu.bookstore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.bookstore.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface BookMapper extends BaseMapper<Book> {


    List<Book> getBookList(@Param("begin") int begin, @Param("size") int size,
                           @Param("condition") Map<String, Object> condition);

    int getTotal(@Param("condition") Map<String, Object> condition);

    List<Book> getBookListByTrolley(@Param("trolleyId") String trolleyId);

    List<Book> getBookListByOrder(@Param("orderId") String orderId);

    void addSales(@Param("bookId") String bookId);
}

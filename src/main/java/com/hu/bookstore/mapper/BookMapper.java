package com.hu.bookstore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.bookstore.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface BookMapper extends BaseMapper<Book> {

    List<Book> getAllBook(@Param("begin") int begin, @Param("size") int size);

    List<Book> getBookList(@Param("begin") int begin, @Param("size") int size,
                           @Param("condition") Map<String, Object> condition);
}

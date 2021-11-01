package com.hu.bookstore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.bookstore.entity.Book;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BookMapper extends BaseMapper<Book> {

}

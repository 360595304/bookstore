package com.hu.bookstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.bookstore.entity.Book;
import com.hu.bookstore.mapper.BookMapper;
import com.hu.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author suhu
 * @createDate 2021-10-31
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> getBookList(int current, int size) {
        return bookMapper.getAllBook((current - 1) * size, size);
    }

    @Override
    public void addBook(Book book) {
        bookMapper.insert(book);
    }

    @Override
    public void update(Book book) {
        bookMapper.updateById(book);
    }


    @Override
    public List<Book> getBookList(Integer current, Integer size, Map<String, Object> condition) {
        return bookMapper.getBookList((current - 1) * size, size, condition);
    }
}

package com.hu.bookstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.bookstore.entity.Book;
import com.hu.bookstore.mapper.BookMapper;
import com.hu.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    }

    @Override
    public void update(Book book) {

    }

    @Override
    public List<Book> getBookList(String value) {
        return null;
    }
}

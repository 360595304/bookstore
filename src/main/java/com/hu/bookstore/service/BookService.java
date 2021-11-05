package com.hu.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.bookstore.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookService extends IService<Book> {

    List<Book> getBookList(int current, int size);

    void addBook(Book book);

    void update(Book book);

    List<Book> getBookList(Integer current, Integer size, Map<String, Object> condition);
}

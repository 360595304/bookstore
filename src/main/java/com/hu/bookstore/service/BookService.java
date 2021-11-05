package com.hu.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.bookstore.entity.Book;

import java.util.List;

public interface BookService extends IService<Book> {

    List<Book> getBookList(int current, int size);

    void addBook(Book book);

    void update(Book book);

    List<Book> getBookList(String value);
}

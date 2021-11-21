package com.hu.bookstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.bookstore.entity.Book;
import com.hu.bookstore.entity.Goods;
import com.hu.bookstore.mapper.BookMapper;
import com.hu.bookstore.mapper.GoodsMapper;
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
    public int getTotalBook(Map<String, Object> condition) {
        return bookMapper.getTotal(condition);
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

    @Override
    public List<Book> getTrolleyBook(String trolleyId) {
        return bookMapper.getBookListByTrolley(trolleyId);
    }

    @Override
    public List<Book> getOrderBook(String orderId) {
        return bookMapper.getBookListByOrder(orderId);
    }

    @Override
    public void addSales(String bookId) {
        bookMapper.addSales(bookId);
    }

    @Override
    public void addView(String bookId) {
        bookMapper.addView(bookId);
    }

    @Override
    public List<Book> getSimilarBook(String type) {
        return bookMapper.getSimilarBook(type);
    }

    @Override
    public List<Book> getNewBook(int num) {
        return bookMapper.getNewBook(num);
    }

    @Override
    public List<Book> getHotBook(int num) {
        return bookMapper.getHotBook(num);
    }

    @Override
    public List<Book> getRecommendedBook(int num) {
        return bookMapper.getRecommendedBook(num);
    }

}

package com.hu.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.bookstore.entity.Book;
import com.hu.bookstore.entity.Goods;
import com.hu.bookstore.vo.GoodsVO;

import java.util.List;
import java.util.Map;

public interface BookService extends IService<Book> {

    int getTotalBook(Map<String,Object> condition);

    void addBook(Book book);

    void update(Book book);

    List<Book> getBookList(Integer current, Integer size, Map<String, Object> condition);

    List<Book> getTrolleyBook(String trolleyId);

    List<Book> getOrderBook(String orderId);

    void addSales(String bookId);

    void addView(String bookId);

    List<Book> getSimilarBook(String type);

    List<Book> getNewBook(int num);

    List<Book> getHotBook(int num);

    List<Book> getRecommendedBook(int num);

}

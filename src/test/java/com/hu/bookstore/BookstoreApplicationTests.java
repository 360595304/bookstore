package com.hu.bookstore;


import com.hu.bookstore.entity.Book;
import com.hu.bookstore.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BookstoreApplicationTests {


    @Autowired
    BookMapper bookMapper;

    @Test
    void contextLoads() {
        List<Book> bookList = bookMapper.getAllBook(0, 1);
        Book book = bookList.get(0);
        System.out.println(book);
        book.setCommend(1);
        bookMapper.updateById(book);
        bookList = bookMapper.getAllBook(0, 1);
        book = bookList.get(0);
        System.out.println(book);
    }

}

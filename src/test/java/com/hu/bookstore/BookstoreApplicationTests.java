package com.hu.bookstore;


import com.hu.bookstore.entity.Book;
import com.hu.bookstore.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class BookstoreApplicationTests {


    @Autowired
    BookMapper bookMapper;

    @Test
    void contextLoads() {
        List<Book> list = bookMapper.getNewBook(6);
        list.forEach(System.out::println);
    }

}

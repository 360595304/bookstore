package com.hu.bookstore;


import com.hu.bookstore.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookstoreApplicationTests {


    @Autowired
    BookMapper bookMapper;

    @Test
    void contextLoads() {
        System.out.println(bookMapper.getAllBook(0,7));
    }

}

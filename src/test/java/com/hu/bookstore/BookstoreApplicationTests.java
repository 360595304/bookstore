package com.hu.bookstore;

import com.hu.bookstore.entity.User;
import com.hu.bookstore.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookstoreApplicationTests {

    @Autowired
    UserMapper mapper;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        User login = mapper.login(user);
        System.out.println(login);
    }

}

package com.hu.bookstore.controller;

import com.hu.bookstore.entity.Book;
import com.hu.bookstore.response.Result;
import com.hu.bookstore.service.BookService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author suhu
 * @createDate 2021-10-31
 */
@RestController
@RequestMapping("/book")
@CrossOrigin
@Api(value = "书籍管理模块", tags = "书籍管理接口")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/getAllBookList")
    @PermitAll
    public Result getAllBookList(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                 @RequestParam(value = "size", defaultValue = "7") Integer size) {
        List<Book> bookList = bookService.getBookList(current, size);
//        System.out.println(bookList);
        Map<String, Object> data = new HashMap<>();
        data.put("bookArr",bookList);
        return Result.ok().data((data));
    }

}

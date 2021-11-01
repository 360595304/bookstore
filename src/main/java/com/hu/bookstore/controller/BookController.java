package com.hu.bookstore.controller;

import com.hu.bookstore.response.Result;
import com.hu.bookstore.service.BookService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAuthority('user:edit')")
    public Result getAllBookList(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                 @RequestParam(value = "size", defaultValue = "7") Integer size) {

        return Result.ok();
    }

}

package com.hu.bookstore.controller;

import com.hu.bookstore.entity.Book;
import com.hu.bookstore.entity.BrowseHistory;
import com.hu.bookstore.response.Result;
import com.hu.bookstore.service.BookService;
import com.hu.bookstore.service.BrowseHistoryService;
import com.hu.bookstore.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    SysUserService userService;

    @Autowired
    BrowseHistoryService browseHistoryService;

    @PostMapping("/getBookList")
    public Result getBookList(@RequestParam(value = "current", defaultValue = "1") Integer current,
                              @RequestParam(value = "size", defaultValue = "7") Integer size,
                              @RequestBody Map<String, Object> condition) {
        List<Book> bookList;
        bookList = bookService.getBookList(current, size, condition);
        int total = bookService.getTotalBook(condition);
        Map<String, Object> data = new HashMap<>();
        data.put("bookArr", bookList);
        data.put("total",total);
        return Result.ok().data(data);
    }

    @PostMapping("/updateBook")
    public Result updateBook(@RequestBody Book newBook) {
        bookService.updateById(newBook);
        return Result.ok();
    }


    @PostMapping("/addBook")
    public Result addBook(@RequestBody Book newBook) {
        bookService.addBook(newBook);
        return Result.ok();
    }

    @DeleteMapping("/deleteBook/{id}")
    public Result deleteBook(@PathVariable String id) {
        bookService.removeById(id);
        return Result.ok();
    }

    // 获取某一本书籍
    @GetMapping("/getBook/{bookId}")
    public Result getBook(@PathVariable String bookId) {
        Book book = bookService.getById(bookId);
        bookService.addView(bookId);
        BrowseHistory history = new BrowseHistory();
        history.setUserId(userService.getUserInfo().getId());
        history.setBookId(bookId);
        browseHistoryService.add(history);
        return Result.ok().data("book", book);
    }

    // 获取系统推荐的书籍
    @GetMapping("/getRecommendedBook/{num}")
    public Result getRecommendedBook(@PathVariable int num) {
        List<Book> bookList = bookService.getRecommendedBook(num);

        return Result.ok().data("bookList", bookList);
    }

    // 获取热门书籍
    @GetMapping("/getHotBook/{num}")
    public Result getHotBook(@PathVariable int num) {
        List<Book> bookList = bookService.getHotBook(num);

        return Result.ok().data("bookList", bookList);
    }

    // 获取新书
    @GetMapping("/getNewBook/{num}")
    public Result getNewBook(@PathVariable int num) {
        List<Book> bookList = bookService.getNewBook(num);

        return Result.ok().data("bookList", bookList);
    }

    // 同类书籍推荐
    @GetMapping("/getSimilarBook/{type}")
    public Result getSimilarBook(@PathVariable String type) {
        List<Book> bookList = bookService.getSimilarBook(type);
        return Result.ok().data("bookList", bookList);
    }
}

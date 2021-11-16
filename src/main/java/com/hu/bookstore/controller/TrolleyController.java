package com.hu.bookstore.controller;

import com.hu.bookstore.entity.Book;
import com.hu.bookstore.entity.SysUser;
import com.hu.bookstore.entity.Trolley;
import com.hu.bookstore.response.Result;
import com.hu.bookstore.service.BookService;
import com.hu.bookstore.service.SysUserService;
import com.hu.bookstore.service.TrolleyService;
import com.hu.bookstore.vo.TrolleyVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author suhu
 * @createDate 2021-11-08
 */
@RestController
@RequestMapping("/trolley")
@CrossOrigin
@Api(value = "购物车模块", tags = "购物车接口")
public class TrolleyController {

    @Autowired
    private TrolleyService trolleyService;

    @Autowired
    private BookService bookService;

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/get")
    public Result getTrolley() {
        TrolleyVO trolleyVO = new TrolleyVO();
        SysUser userInfo = sysUserService.getUserInfo();
        Long id = userInfo.getId();
        Trolley trolley = trolleyService.getByUserId(id);
        trolleyVO.setId(trolley.getId());
        trolleyVO.setUserId(id);
        List<Book> bookList = bookService.getTrolleyBook(trolley.getId());
        trolleyVO.setBookList(bookList);

        return Result.ok().data("trolley", trolleyVO);
    }
}

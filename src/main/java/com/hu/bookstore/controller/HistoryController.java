package com.hu.bookstore.controller;

import com.hu.bookstore.response.Result;
import com.hu.bookstore.service.BookService;
import com.hu.bookstore.service.BrowseHistoryService;
import com.hu.bookstore.service.SysUserService;
import com.hu.bookstore.vo.HistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author suhu
 * @createDate 2021/12/6
 */
@RestController
@CrossOrigin
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private BrowseHistoryService historyService;

    @Autowired
    private SysUserService userService;


    @GetMapping("/getHistory")
    public Result getHistory() {
        List<HistoryVO> history = historyService.getHistory(userService.getUserInfo().getId());

        return Result.ok().data("history", history);
    }

}

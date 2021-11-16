package com.hu.bookstore.vo;

import com.hu.bookstore.entity.Book;
import lombok.Data;

import java.util.List;


/**
 * @author suhu
 * @createDate 2021-11-05
 */
@Data
public class TrolleyVO {

    private String id;
    private Long userId;

    private List<Book> bookList;
}

package com.hu.bookstore.vo;


import lombok.Data;

/**
 * @author suhu
 * @createDate 2021/11/13
 */
@Data
public class GoodsVO {
    private String id;

    private String pictureUrl;

    private String bookId;

    private String bookName;

    private String author;

    private String press;

    private Double price;

    private Integer count;

    private Boolean checked;
}

package com.hu.bookstore.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author suhu
 * @createDate 2021/12/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryVO {
    private String id;
    private String bookId;
    private Date browseTime;
    private String pictureUrl;
}

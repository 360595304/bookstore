package com.hu.bookstore.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author suhu
 * @createDate 2021-11-08
 */
@Data
public class OrderVO {

    String id;

    Date createTime;

    String nickname;

    String recName;

    String recAddress;

    String recPhone;

    Integer state;

    String notes;

    List<GoodsVO> goodsList;
}

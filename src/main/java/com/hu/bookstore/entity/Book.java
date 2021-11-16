package com.hu.bookstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author suhu
 * @createDate 2021-10-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysBook对象", description = "书籍信息表")
@TableName("sys_book")
public class Book {
    @ApiModelProperty("书籍id")
    private String id;

    @ApiModelProperty("书名")
    private String name;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("出版社")
    private String press;

    @ApiModelProperty("原价")
    private Double originalPrice;

    @ApiModelProperty("折扣")
    private Integer discount;

    @ApiModelProperty("折扣价")
    private Double discountPrice;

    @ApiModelProperty("ISBN号")
    @TableField("ISBN")
    private String ISBN;

    @ApiModelProperty("存货")
    private String inventory;

    @ApiModelProperty("图片url")
    private String pictureUrl;

    @ApiModelProperty("出版时间")
    private Date pubDate;

    @ApiModelProperty("简介")
    private String intro;

    @ApiModelProperty("推荐")
    private Boolean commend;

    @ApiModelProperty("状态[下架,上架]")
    private Boolean state;

}

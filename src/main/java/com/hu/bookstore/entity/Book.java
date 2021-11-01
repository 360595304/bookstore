package com.hu.bookstore.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author suhu
 * @createDate 2021-10-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysBook对象", description = "书籍信息表")
public class Book {
    @ApiModelProperty("书籍id")
    String id;

    @ApiModelProperty("书名")
    String name;

    @ApiModelProperty("作者")
    String author;

    @ApiModelProperty("类型")
    String type;

    @ApiModelProperty("出版社")
    String press;

    @ApiModelProperty("原价")
    Double originalPrice;

    @ApiModelProperty("折扣")
    Integer discount;

    @ApiModelProperty("折扣价")
    Double discountPrice;

    @ApiModelProperty("ISBN号")
    String ISBN;

    @ApiModelProperty("存货")
    String inventory;

    @ApiModelProperty("图片url")
    String pictureUrl;

    @ApiModelProperty("简介")
    String intro;

    @ApiModelProperty("推荐 1:推荐")
    Integer commend;

    @ApiModelProperty("状态[0:下架,1:上架]")
    Integer state;

}

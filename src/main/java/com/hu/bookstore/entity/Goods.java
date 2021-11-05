package com.hu.bookstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author suhu
 * @createDate 2021-11-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "sys_goods信息表", description = "货物信息表")
@TableName("sys_goods")
public class Goods {

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "所属订单id")
    private String orderId;

    @ApiModelProperty(value = "所属购物车id")
    private String trolleyId;

    @ApiModelProperty(value = "书id")
    private String bookId;

    @ApiModelProperty(value = "购买数量")
    private Integer count;
}

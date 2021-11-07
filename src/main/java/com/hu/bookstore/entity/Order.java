package com.hu.bookstore.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@ApiModel(value = "SysOrder对象", description = "订单信息表")
@TableName("sys_order")
public class Order {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty("订单id")
    String id;

    @ApiModelProperty("用户id")
    Long userId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    Date createTime;

    @ApiModelProperty("收货地址")
    String recName;

    @ApiModelProperty("收货人电话")
    String recPhone;

    @ApiModelProperty("收货地址")
    String recAddress;

    @ApiModelProperty("订单状态[0:创建，1:已支付，2:配送中,3:待评价,4:已完成,5:异常]")
    Integer state;

    @ApiModelProperty("备注")
    String notes;
}

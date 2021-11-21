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
@ApiModel(value = "SysBrowseHistory对象", description = "浏览记录表")
@TableName("sys_browsing_history")
public class BrowseHistory {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    Long id;

    @ApiModelProperty(value = "用户id")
    Long userId;

    @ApiModelProperty(value = "书籍id")
    String bookId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "浏览时间")
    Date browseTime;

}

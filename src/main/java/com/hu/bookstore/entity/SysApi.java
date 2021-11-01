package com.hu.bookstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 后台接口表
 * </p>
 *
 * @author NieChangan
 * @since 2021-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysBackendApi对象", description="后台接口表")
public class SysApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "API名称")
    private String apiName;

    @ApiModelProperty(value = "API请求地址")
    private String apiUrl;

    @ApiModelProperty(value = "API请求方式：GET、POST、PUT、DELETE")
    private String apiMethod;

    @ApiModelProperty(value = "父ID")
    private String parentId;

    @ApiModelProperty(value = "排序")
    private Integer apiSort;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "0：不可用，1：可用")
    private Integer available;

    @ApiModelProperty(value = "描述")
    private String description;


}

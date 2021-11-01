package com.hu.bookstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author NieChangan
 * @since 2021-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysMenu对象", description="菜单表")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单/按钮ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "上级菜单ID")
    private Long parentId;

    @ApiModelProperty(value = "菜单/按钮名称")
    private String menuName;

    @ApiModelProperty(value = "前端菜单路由URL")
    private String routerUrl;

    @ApiModelProperty(value = "权限标识")
    private String perms;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "类型 0菜单 1按钮")
    private String type;

    @ApiModelProperty(value = "排序")
    private Long orderNum;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "0：不可用，1：可用")
    private Integer available;

    @ApiModelProperty(value = "0:不展开，1：展开")
    private Integer open;

    @ApiModelProperty(value = "子菜单")
    @TableField(exist = false)
    private List<SysMenu> childMenus;

    /*
     * 排序,根据order排序
     */
    public static Comparator<SysMenu> order(){
        Comparator<SysMenu> comparator = (o1, o2) -> {
            if(o1.getOrderNum() != o2.getOrderNum()){
                return (int) (o1.getOrderNum() - o2.getOrderNum());
            }
            return 0;
        };
        return comparator;
    }

}

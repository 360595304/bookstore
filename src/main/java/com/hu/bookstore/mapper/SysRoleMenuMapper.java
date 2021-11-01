package com.hu.bookstore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.bookstore.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色菜单关联表 Mapper 接口
 * </p>
 *
 * @author NieChangan
 * @since 2021-01-07
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

}

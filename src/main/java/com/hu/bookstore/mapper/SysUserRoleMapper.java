package com.hu.bookstore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.bookstore.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统管理 - 用户角色关联表  Mapper 接口
 * </p>
 *
 * @author NieChangan
 * @since 2021-01-07
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}

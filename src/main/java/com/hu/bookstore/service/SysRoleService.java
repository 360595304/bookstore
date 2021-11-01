package com.hu.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.bookstore.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author NieChangan
 * @since 2021-01-07
 */
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> getRoleList();
}

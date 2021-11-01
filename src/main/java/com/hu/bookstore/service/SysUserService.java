package com.hu.bookstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.bookstore.entity.SysUser;
import com.hu.bookstore.vo.UserVO;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author NieChangan
 * @since 2021-01-07
 */
public interface SysUserService extends IService<SysUser> {

    void add(SysUser sysUser);

    SysUser getUserInfo();

    Page<SysUser> getUserList(Page<SysUser> page, UserVO userVO);

    List<Long> hasRoleIds(Long id);

    void assignRoles(Long id, Long[] rids);
}

package com.hu.bookstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.bookstore.entity.SysRole;
import com.hu.bookstore.mapper.SysRoleMapper;
import com.hu.bookstore.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author NieChangan
 * @since 2021-01-07
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 获取角色列表
     * @return
     */
    @Override
    public List<SysRole> getRoleList() {
        List<SysRole> roles = sysRoleMapper.selectList(null);
        return roles;
    }
}

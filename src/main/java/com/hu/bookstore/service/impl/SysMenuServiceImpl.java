package com.hu.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.bookstore.entity.SysMenu;
import com.hu.bookstore.mapper.SysMenuMapper;
import com.hu.bookstore.service.SysMenuService;
import com.hu.bookstore.utils.MenuTreeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author NieChangan
 * @since 2021-01-07
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> buildMenuTree(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        System.out.println(name+"------------>");
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("type","0");
        List<SysMenu> rootMenu = sysMenuMapper.getMenuListByUserName(name);
        return MenuTreeBuilder.build(rootMenu);
    }
}

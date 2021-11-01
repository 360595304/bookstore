package com.hu.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.bookstore.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author NieChangan
 * @since 2021-01-07
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> buildMenuTree();

}

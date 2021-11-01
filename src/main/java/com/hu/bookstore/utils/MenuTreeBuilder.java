package com.hu.bookstore.utils;

import com.hu.bookstore.entity.SysMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuTreeBuilder {

    public static List<SysMenu> build(List<SysMenu> rootMenu) {
        //查看结果
        rootMenu.forEach(menu -> {
            System.out.println(menu.toString());
        });
        //最后的结果
        List<SysMenu> menuList = new ArrayList<>();
        //先找到所有的一级菜单
        for (int i = 0; i < rootMenu.size(); i++) {
            //如果parentId为0,则代表父级菜单,根据数据库需求去,有些是写的null
            if (rootMenu.get(i).getParentId() == 0) {
                //将一级菜单存到menuList
                menuList.add(rootMenu.get(i));
            }
        }
        /* 根据Menu类的order排序 */
        Collections.sort(rootMenu,SysMenu.order());
        // 为一级菜单设置子菜单，getChild()是递归调用的方法
        for (SysMenu menu : menuList) {
            menu.setChildMenus(getChild(menu.getId(), rootMenu));
        }

        //返回最终的菜单集合
        return menuList;
    }

    private static List<SysMenu> getChild(Long id, List<SysMenu> rootMenu) {
        //构建子菜单集合
        List<SysMenu> childList = new ArrayList<>();
        //遍历父级菜单集合
        for (SysMenu menu : rootMenu) {
            //不是父级
            if (menu.getParentId() != 0) {
                //如果传递过来的父级ID等于循环出来的菜单ID,则认为当前ID为子菜单
                if (menu.getParentId().equals(id) && menu.getType().equals("0")) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (SysMenu menu : childList) {
            menu.setChildMenus(getChild(menu.getId(), rootMenu));
        }
        //排序
        if(childList.size()>2){
            Collections.sort(childList,SysMenu.order());
        }
        //递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}

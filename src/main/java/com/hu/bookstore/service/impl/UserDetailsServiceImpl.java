package com.hu.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hu.bookstore.entity.SysMenu;
import com.hu.bookstore.entity.SysUser;
import com.hu.bookstore.mapper.SysMenuMapper;
import com.hu.bookstore.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 通过名称加载用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username==null||"".equals(username)){
            throw new RuntimeException("用户名不能为空");
        }
        //根据用户名查询用户信息
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername,username);
        SysUser user = sysUserMapper.selectOne(wrapper);
        if(user==null){
            throw new UsernameNotFoundException(String.format("%s用户不存在",username));
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        //查询权限集合
        List<SysMenu> menuList = sysMenuMapper.getMenuListByUserName(username);
        List<String> permsList = menuList.parallelStream()
                .filter(Objects::nonNull)
                .map(SysMenu::getPerms)
                .collect(Collectors.toList());

        permsList.stream().filter(Objects::nonNull)
                .filter(s->!s.isEmpty())
                .collect(Collectors.toList()).forEach(perms->{
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(perms);
            authorities.add(authority);
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
}

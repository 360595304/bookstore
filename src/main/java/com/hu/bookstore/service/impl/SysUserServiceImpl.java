package com.hu.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.bookstore.entity.SysRole;
import com.hu.bookstore.entity.SysUser;
import com.hu.bookstore.entity.SysUserRole;
import com.hu.bookstore.entity.Trolley;
import com.hu.bookstore.enums.UserStatusEnum;
import com.hu.bookstore.enums.UserTypeEnum;
import com.hu.bookstore.exception.BusinessException;
import com.hu.bookstore.mapper.SysRoleMapper;
import com.hu.bookstore.mapper.SysUserMapper;
import com.hu.bookstore.mapper.SysUserRoleMapper;
import com.hu.bookstore.mapper.TrolleyMapper;
import com.hu.bookstore.response.ResultCode;
import com.hu.bookstore.service.SysUserService;
import com.hu.bookstore.service.TrolleyService;
import com.hu.bookstore.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author NieChangan
 * @since 2021-01-07
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private TrolleyMapper trolleyMapper;

    /**
     * 添加用户信息
     *
     * @param sysUser
     */
    @Transactional
    @Override
    public void add(SysUser sysUser) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        // 检查用户名是否存在
        wrapper.eq(SysUser::getUsername, sysUser.getUsername());
        if (this.baseMapper.selectCount(wrapper) != 0) {
            throw new BusinessException(ResultCode.USER_ACCOUNT_ALREADY_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_ALREADY_EXIST.getMessage());
        }

        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUser.setEnabled(UserStatusEnum.AVAILABLE.getStatusCode());
        sysUser.setType(UserTypeEnum.SYSTEM_USER.getTypeCode());
        sysUser.setAvatar("https://img1.baidu.com/it/u=756226200,2409592995&fm=26&fmt=auto");
        baseMapper.insert(sysUser);
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(sysUser.getId());
        userRole.setRoleId(5L);
        sysUserRoleMapper.addUserRole(userRole);
        Trolley trolley = new Trolley();
        trolley.setUserId(sysUser.getId());
        trolleyMapper.insert(trolley);
    }

    /**
     * 获取登录的用户信息
     *
     * @return
     */
    @Override
    public SysUser getUserInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        //根据用户名查询用户信息
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username);
        return sysUserMapper.selectOne(wrapper);
    }

    /**
     * 分页+条件查询用户信息
     *
     * @param page
     * @param userVO
     * @return
     */
    @Override
    public Page<SysUser> getUserList(Page<SysUser> page, UserVO userVO) {
        List<SysUser> userList = sysUserMapper.getUserList(page, userVO);
        //返回用户集合
        return page.setRecords(userList);
    }

    /**
     * 根据用户ID获取已有的角色ID集合
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public List<Long> hasRoleIds(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        //账号不存在
        if (sysUser == null) {
            throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
        }
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(wrapper);
        //定义一个已有角色ID的集合
        List<Long> hasRoleIds = new ArrayList<>();
        //如果查询到的集合不为空
        if (!CollectionUtils.isEmpty(sysUserRoles)) {
            for (SysUserRole sysUserRole : sysUserRoles) {
                //根据角色ID查询角色信息
                SysRole sysRole = sysRoleMapper.selectById(sysUserRole.getRoleId());
                //如果存在则添加到集合中
                if (sysRole != null) {
                    hasRoleIds.add(sysRole.getId());
                }
            }
        }
        return hasRoleIds;
    }

    /**
     * 分配角色
     *
     * @param id   用户id
     * @param rids 角色数组
     */
    @Override
    @Transactional
    public void assignRoles(Long id, Long[] rids) {
        //删除之前用户的所有角色
        /*SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null) {
            throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
        }
        //删除之前分配的角色
        LambdaQueryWrapper<>
        sysUserRoleMapper.delete()*/
    }
}

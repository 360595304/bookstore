package com.hu.bookstore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu.bookstore.entity.SysUser;
import com.hu.bookstore.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author NieChangan
 * @since 2021-01-07
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> getUserList(@Param("page") Page<SysUser> page, @Param("userVO") UserVO userVO);
}

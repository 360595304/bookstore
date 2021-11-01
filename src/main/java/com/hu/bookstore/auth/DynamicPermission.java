package com.hu.bookstore.auth;

import com.hu.bookstore.entity.SysApi;
import com.hu.bookstore.mapper.SysApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component("dynamicPermission")
public class DynamicPermission {

    @Autowired
    private SysApiMapper sysApiMapper;

    public boolean hasPermission(HttpServletRequest request,
                                 Authentication authentication){

        Object principal = authentication.getPrincipal();

        if(principal instanceof UserDetails){
            //得到当前的账号
            UserDetails userDetails = (UserDetails)principal;
            //获取当前登录账号的用户名称
            String username = userDetails.getUsername();
            //通过用户名称获取API列表
            List<SysApi> apiList = sysApiMapper.getApiListByUserName(username);

            /*
                这个类由spring提供
                背景：在做uri匹配规则发现这个类，根据源码对该类进行分析，它主要用来做类URLs字符串匹配；
                可以做URLs匹配，规则如下
                    ？匹配一个字符
                    *匹配0个或多个字符
                    **匹配0个或多个目录
                用例如下
                    /trip/api*//*x    匹配 /trip/api/x，/trip/api/ax，/trip/api/abx ；但不匹配 /trip/abc/x；
                    /trip/a/a?x    匹配 /trip/a/abx；但不匹配 /trip/a/ax，/trip/a/abcx
                    /**//*api/alie    匹配 /trip/api/alie，/trip/dax/api/alie；但不匹配 /trip/a/api
                    *//**//**.htmlm   匹配所有以.htmlm结尾的路径
             */
            AntPathMatcher antPathMatcher = new AntPathMatcher();

            //当前访问路径  localhost:8080/system/role/listPage
            String uri = request.getRequestURI();
            //提交类型
            String method = request.getMethod();

            //anyMatch：判断的条件里，任意一个元素成功，返回true
            boolean flag = apiList.stream().anyMatch(api->{
                //判断访问的uri是否满足数据库中存放的url(通过正则表达式进行判断)
                //   /role/authority/?   /role/authority/12
                boolean match = antPathMatcher.match(api.getApiUrl(), uri);

                //判断请求方式是否和数据库中匹配（数据库存储：GET,POST,PUT,DELETE）
                boolean equals = api.getApiMethod().equals(method);

                //两个都为真则满足条件
                return match && equals;
            });
            if(flag){
                return flag;
            }else{
                throw  new MyAccessDeniedException("您没有访问该API的权限！");
            }
        }else{
            throw  new MyAccessDeniedException("不是UserDetails类型！");
        }
    }
}

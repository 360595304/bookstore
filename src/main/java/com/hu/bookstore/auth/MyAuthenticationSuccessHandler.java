package com.hu.bookstore.auth;

import com.hu.bookstore.response.Result;
import com.hu.bookstore.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功操作
 */
@Component
public class MyAuthenticationSuccessHandler extends JSONAuthentication implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        //获取当前的登录用户
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = null;
        if(principal instanceof UserDetails){
            userDetails = (UserDetails)principal;
        }
        System.out.println(userDetails.getUsername()+"+++++++++>");
        Map<String,Object> map = new HashMap<>();
        map.put("msg","登录成功");
        map.put("token",jwtTokenUtil.generateToken(userDetails));

        //装入token
        Result result = Result.ok().data(map);
        //输出
        this.WriteJSON(request, response, result);

    }
}

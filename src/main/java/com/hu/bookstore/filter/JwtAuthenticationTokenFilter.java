package com.hu.bookstore.filter;

import com.hu.bookstore.exception.BusinessException;
import com.hu.bookstore.response.ResultCode;
import com.hu.bookstore.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("jwtAuthenticationTokenFilter")
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private final String header = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        //判断token是否合法,如果token是合法的,那就意味着已经进行过登录了
        //从请求头中获取token
        String headerToken = request.getHeader(header);
        System.out.println("headerToken = " + headerToken);
        System.out.println("request getMethod = " + request.getMethod());

        //判断token是否为空
        if (!StringUtils.isEmpty(headerToken)) {
            //postMan测试时，自动加入的前缀要去掉。
            String token = headerToken.replace("Bearer", "").trim();
            System.out.println("token = " + token);
            //判断令牌是否过期，默认是一周
            //比较好的解决方案是：
            //登录成功获得token后，将token存储到数据库（redis）
            //将数据库版本的token设置过期时间为15~30分钟
            //如果数据库中的token版本过期，重新刷新获取新的token
            //注意：刷新获得新token是在token过期时间内有效。
            //如果token本身的过期（1周），强制登录，生成新token。

            //假设token是无效的
            boolean check = false;

            try {
                //验证token是否过期
                check = this.jwtTokenUtil.isTokenExpired(token);
            } catch (Exception e) {
                //过期则抛出异常
                try {
                    throw new BusinessException(ResultCode.USER_ACCOUNT_EXPIRED.getCode(), "令牌已过期，请重新登录。" + e.getMessage());
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }
            //如果没有过期,则要验证token的合法性了
            if (!check) {
                //通过令牌获取用户名称
                String username = jwtTokenUtil.getUsernameFromToken(token);
                System.out.println("username = " + username);

                //拿到用户名之后,重新通过userService的loadUserByUsername方法重新得到一个用户
                //放到securityContext的上下文中就行了
                //判断用户不为空，且SecurityContextHolder授权信息还是空的
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    //验证令牌有效性
                    boolean validata = false;
                    try {
                        validata = jwtTokenUtil.validateToken(token, userDetails);
                    } catch (Exception e) {
                        try {
                            throw new Throwable("验证token无效:" + e.getMessage());
                        } catch (Throwable ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (validata) {
                        // 将用户信息存入 authentication，方便后续校验
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );
                        //
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }
}

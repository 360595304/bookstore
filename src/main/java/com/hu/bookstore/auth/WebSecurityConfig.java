package com.hu.bookstore.auth;

import com.hu.bookstore.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private MyLogoutHandler myLogoutHandler;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private MyExpiredSessionStrategy myExpiredSessionStrategy;

    //jwt拦截器
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //获取用户账号密码及权限信息
        auth.userDetailsService(userDetailsService)
                // 设置默认的加密方式（强hash方式加密）
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET,
                "/favicon.ico",
                "/**/*.png",
                "/**/*.ttf",
                "/*.html",
                "/**/*.css",
                "/**/*.js");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.cors().and().csrf().disable();

        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/doc.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .anyRequest().permitAll();*/

        //第1步：解决跨域问题。cors 预检请求放行,让Spring security 放行所有preflight request（cors 预检请求）
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

        //第2步：让Security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().cacheControl();

        //第3步：请求权限配置
        http.authorizeRequests()
                //.antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.GET,"/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.GET,"/doc.html").permitAll()
                .antMatchers(HttpMethod.GET,"/webjars/**").permitAll()
                .antMatchers(HttpMethod.GET,"/v2/**").permitAll()
                .antMatchers(HttpMethod.GET,"/swagger-resources/**").permitAll()
                .antMatchers(HttpMethod.GET,"/users/getUserInfo").permitAll()
                .antMatchers(HttpMethod.POST,"/users/add").permitAll()
                .anyRequest().access("@dynamicPermission.hasPermission(request,authentication)");

        //第4步：拦截token，并检测。在 UsernamePasswordAuthenticationFilter 之前添加 JwtAuthenticationTokenFilter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //第5步：处理异常情况：认证失败和权限不足
        http.exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint).accessDeniedHandler(myAccessDeniedHandler);

        //第6步：登录,因为使用前端发送JSON方式进行登录，所以登录模式不设置也是可以的。
        http.formLogin().loginPage("/login").successHandler(myAuthenticationSuccessHandler).failureHandler(myAuthenticationFailureHandler);

        //第七步：设置会话管理：同一账号同时登录最大用户数,会话失效(账号被挤下线)处理逻辑
        http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(myExpiredSessionStrategy);

        //第8步：退出
        http.logout().addLogoutHandler(myLogoutHandler).logoutSuccessHandler(myLogoutSuccessHandler).deleteCookies("JSESSIONID");
    }
}

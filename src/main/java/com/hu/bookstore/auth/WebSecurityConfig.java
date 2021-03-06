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

    //jwt?????????
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //???????????????????????????????????????
        auth.userDetailsService(userDetailsService)
                // ?????????????????????????????????hash???????????????
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET,
                "/favicon.ico",
                "/**/*.png",
                "/**/*.ttf",
                "/*.html",
                "/**/*.jpg",
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

        //???1???????????????????????????cors ??????????????????,???Spring security ????????????preflight request???cors ???????????????
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

        //???2?????????Security??????????????????HttpSession??????????????????HttpSession?????????SecurityContext
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().cacheControl();

        //???3????????????????????????
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/swagger-ui.html").permitAll()
//                .antMatchers(HttpMethod.GET,"/doc.html").permitAll()
//                .antMatchers(HttpMethod.GET,"/webjars/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/v2/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/swagger-resources/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/users/getUserInfo").permitAll()
//                .antMatchers(HttpMethod.POST,"/users/add").permitAll()
                .anyRequest().access("@dynamicPermission.hasPermission(request,authentication)");

        //???4????????????token?????????????????? UsernamePasswordAuthenticationFilter ???????????? JwtAuthenticationTokenFilter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //???5??????????????????????????????????????????????????????
        http.exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint).accessDeniedHandler(myAccessDeniedHandler);

        //???6????????????,????????????????????????JSON??????????????????????????????????????????????????????????????????
        http.formLogin().loginPage("/login").successHandler(myAuthenticationSuccessHandler).failureHandler(myAuthenticationFailureHandler);

        //????????????????????????????????????????????????????????????????????????,????????????(??????????????????)????????????
        http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(myExpiredSessionStrategy);

        //???8????????????
        http.logout().addLogoutHandler(myLogoutHandler).logoutSuccessHandler(myLogoutSuccessHandler).deleteCookies("JSESSIONID");
    }
}

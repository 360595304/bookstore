package com.hu.bookstore.auth;

import com.hu.bookstore.response.Result;
import com.hu.bookstore.response.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("myAccessDeniedHandler")
public class MyAccessDeniedHandler extends JSONAuthentication implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        System.out.println(e.getMessage());
        Result result = Result.error(ResultCode.NO_PERMISSION).message(e.getMessage());

        this.WriteJSON(request,response,result);
    }
}

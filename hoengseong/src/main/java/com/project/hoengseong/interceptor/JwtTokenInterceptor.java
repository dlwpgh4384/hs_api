package com.project.hoengseong.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JwtTokenInterceptor implements HandlerInterceptor {
	/*
    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {
        final String header = request.getHeader(AuthConstants.AUTH_HEADER);

        if(header != null){
            final String token = TokenUtils.getTokenFromHeader(header);
            if(TokenUtils.isValidToken(token)){
                return true;
            }
        }
        response.sendRedirect("/error/unauthorized");
        return false;
    }
    */
}
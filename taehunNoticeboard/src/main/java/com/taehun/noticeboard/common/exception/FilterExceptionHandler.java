package com.taehun.noticeboard.common.exception;

import com.taehun.noticeboard.common.response.ResponseCode;
import com.taehun.noticeboard.common.response.ResponseMessage;
import com.taehun.noticeboard.security.exception.TokenForgeryException;
import com.taehun.noticeboard.security.jwt.support.CookieSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.GenericFilterBean;

public class FilterExceptionHandler extends GenericFilterBean {
    public FilterExceptionHandler() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (UsernameNotFoundException var6) {
            CookieSupport.deleteJwtTokenInCookie((HttpServletResponse)response);
            setErrorResponse((HttpServletResponse)response, 401, "토큰이 변조되었거나 유효하지 않습니다.");
        } catch (TokenForgeryException var7) {
            CookieSupport.deleteJwtTokenInCookie((HttpServletResponse)response);
            setErrorResponse((HttpServletResponse)response, 401, "토큰이 만료되었거나 유효하지 않습니다.");
        } catch (ServletException var8) {
            ServletException e = var8;
            Throwable rootCause = this.findRootCause(e);
            if (rootCause != null) {
                setErrorResponse((HttpServletResponse)response, HttpStatus.BAD_REQUEST.value(), rootCause.getMessage());
            } else {
                setErrorResponse((HttpServletResponse)response, HttpStatus.BAD_REQUEST.value(), e.getMessage());
            }
        }

    }

    public Throwable findRootCause(Throwable throwable) {
        Throwable cause = throwable.getCause();
        return cause == null ? throwable : this.findRootCause(cause);
    }

    public static void setErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(status);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        ResponseMessage errorResponse = ResponseMessage.of(ResponseCode.AUTHENTICATION_ERROR, message);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

    public static void setSuccessResponse(HttpServletResponse response, ResponseCode responseCode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(200);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        ResponseMessage errorResponse = ResponseMessage.of(responseCode);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
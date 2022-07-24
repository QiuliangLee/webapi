package com.bocft.bocpet.webapi.shiro.custom;

import cn.hutool.json.JSONUtil;
import com.bocft.bocpet.webapi.common.enums.ResultCodeEnum;
import com.bocft.bocpet.webapi.common.pojo.Result;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 重写过滤器中的onAccessDenied方法
 * 实现在未登录时返回JSON
 */
public class CustomAuthenticationFilter extends FormAuthenticationFilter {
    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return true;//不拦截，直接返回ture
//        if (this.isLoginRequest(request, response)) {
//            if (this.isLoginSubmission(request, response)) {
//                if (log.isTraceEnabled()) {
//                    log.trace("Login submission detected.  Attempting to execute login.");
//                }
//
//                return this.executeLogin(request, response);
//            } else {
//                if (log.isTraceEnabled()) {
//                    log.trace("Login page view.");
//                }
//
//                return true;
//            }
//        } else {
//            HttpServletRequest req = (HttpServletRequest) request;
//            HttpServletResponse resp = (HttpServletResponse) response;
//            if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
//                resp.setStatus(HttpStatus.OK.value());
//                return true;
//            } else {
//                if (log.isTraceEnabled()) {
//                    log.trace("Attempting to access a path which requires authentication.  Forwarding to the Authentication url [{}]", this.getLoginUrl());
//                }
//                resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
//                resp.setHeader("Access-Control-Allow-Credentials", "true");
//                resp.setContentType("application/json; charset=utf-8");
//                resp.setCharacterEncoding("UTF-8");
//                resp.setStatus(HttpStatus.UNAUTHORIZED.value());
//                Result result = Result.err(ResultCodeEnum.UNAUTHENTICATED);
//                PrintWriter out = resp.getWriter();
//                out.println(JSONUtil.toJsonStr(result));
//                out.flush();
//                out.close();
//                return false;
//            }
//        }
    }
}

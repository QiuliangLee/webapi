package com.bocft.bocpet.webapi.common.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.common.util.CaptchaUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//配置拦截路径
@WebFilter(urlPatterns = {"/auth/login"})
public class CaptchaFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String captchaId = request.getHeader("CaptchaId");
        String captcha = request.getHeader("Captcha");
        Result result = null;
        if (StrUtil.isBlank(captcha)) {
            result = Result.err(-1, "验证码为空");
        } else {
            try {
                CaptchaUtils.checkCode("CaptchaId:" + captchaId, captcha);
            } catch (Exception e) {
                result = Result.err(-1, e.getMessage());
            }
        }
        if (result != null) {
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.OK.value());
            PrintWriter out = response.getWriter();
            out.println(JSONUtil.toJsonStr(result));
            out.flush();
            out.close();
        } else {
            //执行
            filterChain.doFilter(servletRequest, servletResponse);
            CaptchaUtils.invalidateCode("CaptchaId:" + captchaId);
        }

    }

    @Override
    public void destroy() {

    }
}
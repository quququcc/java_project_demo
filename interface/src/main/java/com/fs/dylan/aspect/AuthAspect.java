package com.fs.dylan.aspect;

import cn.hutool.jwt.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dylan
 * @title: AuthAspect
 * @projectName dylan
 * @description:
 * @date 2023/8/16
 */

@Aspect
@Component
public class AuthAspect {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private final static List<String> list = Arrays.asList(
            "/api/auth/login",
            "/api/auth/register",
            "/ads/download",
            "/ads/trends/download",
            "/i18n/getMessage",
            "/api/excel/uploadAddress",
            "/api/excel/downloadAddress",
            "/api/excel/trendsDownloadAddress",
            "/i18n/getArgsMessage",
            "/i18n/getArgsMessage",
            "/i18n/logging"
    );

    @Value("${jwt.key}")
    private String key;

    @Pointcut("execution(* com.fs.dylan.controller.*.*(..))")
    public void checkPointCut() {}

    @Before(value = "checkPointCut()")
    public void validateToken() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String token = request.getHeader("token");
        String requestURI = request.getRequestURI();
        if (list.contains(requestURI)) {
            return;
        }

        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("token为空");
        }

        Object o = redisTemplate.opsForValue().get(key);
        if (o == null) {
            throw new RuntimeException("token已过期");
        }

        boolean verify = JWTUtil.verify(token, key.getBytes());
        if (!verify) {
            throw new RuntimeException("token验证失败");
        }
    }
}

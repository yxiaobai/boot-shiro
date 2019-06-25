package com.spbs.bootshiro.common.aspect;

import com.spbs.bootshiro.common.config.FebsProperties;
import com.spbs.bootshiro.common.utils.HttpContextUtils;
import com.spbs.bootshiro.common.utils.IPUtils;
import com.spbs.bootshiro.system.domain.TLog;
import com.spbs.bootshiro.system.domain.TUser;
import com.spbs.bootshiro.system.service.LogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * AOP 记录用户操作日志
 *
 * @author MrBird
 * @link https://mrbird.cc/Spring-Boot-AOP%20log.html
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private FebsProperties febsProperties;

    @Autowired
    private LogService logService;


    @Pointcut("@annotation(com.spbs.bootshiro.common.annotation.Log)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        // 执行方法
        result = point.proceed();
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        String ip = IPUtils.getIpAddr(request);
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        if (febsProperties.isOpenAopLog()) {
            // 保存日志
            TUser user = (TUser) SecurityUtils.getSubject().getPrincipal();
            TLog log = new TLog();
            log.setUsername(user.getUserName());
            log.setIp(ip);
            log.setTime(time);
            logService.saveLog(point, log);
        }
        return result;
    }
}

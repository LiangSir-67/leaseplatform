package cn.leaseplatform.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 实现注解
 * @Author: 梁歪歪 <1732178815@qq.com>
 * @Description: blog <liangyy.cn>
 * @Create 2021-04-01-15:11
 */
@Aspect
@Component
@Slf4j
public class ReleaseTokenAnno {

    @Pointcut("@annotation(cn.leaseplatform.interceptors.ReleaseToken)")
    private void cut(){}

    @Before("cut()")
    public void before(){
        log.info("放行接口！！！");
    }
}

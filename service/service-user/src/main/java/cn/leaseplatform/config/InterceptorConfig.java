package cn.leaseplatform.config;

import cn.leaseplatform.interceptors.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 梁歪歪 <1732178815@qq.com>
 * @Description: blog <liangyy.cn>
 * @Create 2021-04-01-9:58
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private String patterns[] = {
            "/user/userRegister",
            "/user/userLogin",
            "/test/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/error","/doc.html",
            "/v2/api-docs/**",
            "/v2/api-docs-ext/**"
    };
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**")             // 不放行，token验证
                .excludePathPatterns(patterns);   // 放行
    }
}

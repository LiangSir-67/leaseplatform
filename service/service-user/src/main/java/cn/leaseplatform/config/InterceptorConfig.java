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
            "/service-user/user/userRegister",
            "/service-user/user/userLogin",
            "/service-user/test/**",
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

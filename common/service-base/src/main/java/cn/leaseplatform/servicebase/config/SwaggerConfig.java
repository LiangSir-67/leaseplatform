package cn.leaseplatform.servicebase.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * swagger的配置类
 */
@Configuration
@EnableSwagger2
@EnableKnife4j  //开启knife4j的增强功能
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket webApiConfig(){
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        ArrayList<Parameter> parameters = new ArrayList<>();
        parameterBuilder.name("token").description("用户token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();   // header中的token可以传空值
        parameters.add(parameterBuilder.build());   // 根据每个方法名知道当前方法在设置什么参数

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("共享租赁平台API文档")
                .description("本文档描述了共享租赁平台微服务接口定义")
                .version("1.0")
                .contact(new Contact("liangyy","liangyy.cn","1732178815@qq.com"))
                .build();
    }
}

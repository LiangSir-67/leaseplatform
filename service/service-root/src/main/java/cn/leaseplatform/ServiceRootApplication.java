package cn.leaseplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ServiceRootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceRootApplication.class,args);
    }
}

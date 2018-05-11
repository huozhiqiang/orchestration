package com.yss.ms.config;


import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * mybatis mapper 扫描配置类
 * 
 * @author huoZhiQiang
 * @date 2017-12-12 16:27:51
 * @since 1.7
 */
@Configuration
@AutoConfigureAfter(MybatisConfiguration.class)
public class MapperConfiguration implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver;

    private String basePackage;

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){

        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(basePackage);
        return mapperScannerConfigurer;
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, null);
        this.basePackage = propertyResolver.getProperty("mybatis.basePackage");
    }
}
package com.danihc.springboot.di.app.springboot_di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.context.annotation.RequestScope;

import com.danihc.springboot.di.app.springboot_di.repositories.ProductRepository;
import com.danihc.springboot.di.app.springboot_di.repositories.ProductRepositoryJson;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Bean("productJson")
    @RequestScope
    ProductRepository productRepositoryJson(){
        return new ProductRepositoryJson();
    }
}

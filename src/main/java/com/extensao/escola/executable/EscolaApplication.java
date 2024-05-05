package com.extensao.escola.executable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.extensao.escola"})
@EntityScan(basePackages = {"com.extensao.escola.model"})
@EnableJpaRepositories("com.extensao.escola.repository")
@EnableConfigurationProperties
@EnableCaching
@EnableScheduling
public class EscolaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EscolaApplication.class, args);
    }
}
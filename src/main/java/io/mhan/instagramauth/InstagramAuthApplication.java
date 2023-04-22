package io.mhan.instagramauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableFeignClients
public class InstagramAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstagramAuthApplication.class, args);
    }

}

package com.espe.micro_conductor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroConductorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroConductorApplication.class, args);
    }
}

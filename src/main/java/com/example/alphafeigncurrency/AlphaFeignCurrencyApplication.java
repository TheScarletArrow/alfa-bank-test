package com.example.alphafeigncurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlphaFeignCurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlphaFeignCurrencyApplication.class, args);
    }

}

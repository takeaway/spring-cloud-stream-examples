package com.example.democonsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class DemoconsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoconsumerApplication.class, args);
    }

    @Bean
    public Consumer<String> consumerBean() {
        return System.out::println;
    }
}

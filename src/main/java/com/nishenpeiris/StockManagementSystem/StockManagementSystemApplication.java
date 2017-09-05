package com.nishenpeiris.StockManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class StockManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockManagementSystemApplication.class, args);
    }
}

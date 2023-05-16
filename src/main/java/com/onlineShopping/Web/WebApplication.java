package com.onlineShopping.Web;

import com.onlineShopping.Web.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class WebApplication implements CommandLineRunner {

    UsersService usersService;

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        usersService.setDefaultPasswordForAllUsers();
    }
}

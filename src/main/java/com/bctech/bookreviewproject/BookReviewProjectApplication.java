package com.bctech.bookreviewproject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BookReviewProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookReviewProjectApplication.class, args);
    }

//    @Bean
//    CommandLineRunner runner(RoleService roleService) {
//        return args -> {
//            Role role = new Role(null, RoleType.ROLE_USER.toString());
//            roleService.save(role);
//        };
//    }

}

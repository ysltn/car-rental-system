package kz.usenkhan.yersultan.it22310.carrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class UsenkhanYersultanCarRentalApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsenkhanYersultanCarRentalApplication.class, args);
    }
}


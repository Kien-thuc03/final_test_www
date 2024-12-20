package edu.iuh.fit.phone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PhoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneApplication.class, args);
    }

}

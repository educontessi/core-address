package io.github.educontessi.core.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CoreAddressApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreAddressApplication.class, args);
    }

}

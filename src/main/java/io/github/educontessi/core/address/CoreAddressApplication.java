package io.github.educontessi.core.address;

import io.github.educontessi.core.address.core.config.TagLogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CoreAddressApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoreAddressApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CoreAddressApplication.class, args);
        LOGGER.info("{} Completed initialization", TagLogs.STARTED);
    }

}

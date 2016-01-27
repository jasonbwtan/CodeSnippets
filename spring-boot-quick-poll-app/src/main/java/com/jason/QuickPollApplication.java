package com.jason;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mangofactory.swagger.plugin.EnableSwagger;

/**
 * http://localhost:8080/api-docs
 * http://localhost:8080/swagger-ui/index.html
 * @author Jason
 *
 */
@SpringBootApplication
public class QuickPollApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickPollApplication.class, args);
    }
}
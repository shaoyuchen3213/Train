package com.java.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan("com.java")

public class GateApplication {
    private static final Logger LOG = LoggerFactory.getLogger(GateApplication.class);

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(GateApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("Success Start!");
        LOG.info("Listening on \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }
}

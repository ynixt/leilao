package com.unkapps.leilao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.util.Map;
import java.util.TimeZone;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class LeilaoApplication {
    private static final Logger log = LoggerFactory.getLogger(LeilaoApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(LeilaoApplication.class);
        app.setDefaultProperties(Map.of(
                "spring.profiles.default", "dev"
        ));

        Environment env = app.run(args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\n" +
                        "Aplicação executando!: http://localhost:{}" +
                        "\n\n----------------------------------------------------------",
                env.getProperty("server.port")
        );
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}

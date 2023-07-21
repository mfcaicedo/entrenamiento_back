package com.hexa.entrenamiento.dominio.hexa;

import io.undertow.UndertowOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"com.hexa.entrenamiento.dominio"})
@EntityScan(basePackages = "com.hexa.entrenamiento.dominio")
@EnableTransactionManagement
@EnableEurekaClient
public class DominioHexaApplication {
    public static void main(String[] args) {
        SpringApplication.run(DominioHexaApplication.class, args);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    UndertowServletWebServerFactory undertowServletWebServerFactory() {
        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
        factory.addBuilderCustomizers(
                builder -> {
                    builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true)
                            .setServerOption(UndertowOptions.HTTP2_SETTINGS_ENABLE_PUSH, true);
                });

        return factory;
    }


}

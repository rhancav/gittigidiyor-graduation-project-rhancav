package dev.loanapplicationui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@SpringBootApplication
public class LoanApplicationUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanApplicationUiApplication.class, args);
    }
    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }
    @Bean
    RestTemplate restTemplateBuild(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }


}

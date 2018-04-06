package com.acme;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import static org.springframework.boot.Banner.Mode.OFF;
import static org.springframework.boot.WebApplicationType.NONE;

@Slf4j
@SpringBootApplication
public class AppApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder().sources(AppApplication.class).bannerMode(OFF).web(NONE)
        .run(args);
  }

  @Bean
  GreetingService customGreetingService() {
    return () -> "Custom Greeting";
  }

  @Bean
  CommandLineRunner commandLineRunner(GreetingService greetingService) {
    return args -> log.info(greetingService.greet());
  }

}

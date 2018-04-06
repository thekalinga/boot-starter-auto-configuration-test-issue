package com.acme;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreetingAutoConfiguration {
  @Bean
  @ConditionalOnMissingBean(GreetingService.class)
  GreetingService greetingService() {
    return new HelloWorldGreetingService();
  }
}


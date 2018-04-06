package com.acme;

import org.junit.After;
import org.junit.Test;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

public class GreetingAutoConfigurationTests {

  private AnnotationConfigApplicationContext context;

  @Test
  public void expectDefaultMessage() {
    load(new Class[] {AutoConfiguration.class});
    GreetingService service = context.getBean(GreetingService.class);
    assertThat(service.greet()).isEqualTo("Hello world");
  }

  @Test
  public void expectOverriddenMessage() {
    load(new Class[] {OverriddenAutoConfiguration.class});
    GreetingService service = context.getBean(GreetingService.class);
    assertThat(service.greet()).isEqualTo("Overridden");
  }

  private void load(Class<?>[] classes, String... environmentPairs) {
    context = new AnnotationConfigApplicationContext();
    context.register(classes);
    TestPropertyValues.of(environmentPairs).applyTo(context);
    context.refresh();
  }

  @After
  public void afterEachTest() {
    if (context != null) {
      context.close();
    }
  }

  @Configuration
  @ImportAutoConfiguration(GreetingAutoConfiguration.class)
  class AutoConfiguration {
  }


  @Configuration
  @Import(AutoConfiguration.class)
  class OverriddenAutoConfiguration {
    @Bean
    GreetingService overriddenGreetingService() {
      return () -> "Overridden";
    }
  }

}

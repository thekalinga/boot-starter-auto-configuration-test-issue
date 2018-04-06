package com.acme;

public class HelloWorldGreetingService implements GreetingService {
  @Override
  public String greet() {
    return "Hello World";
  }
}

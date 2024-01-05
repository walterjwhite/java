package com.walterjwhite.examples.spring.rest;

// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  // example, this can be any property provided we set it.
  //  @Value("${spring.application.name}")
  //  String appName;

  @GetMapping("/throw")
  public String testThrow() {
    // the exception handler should show a pretty message
    throw new IllegalStateException("This is a test of the emergency broadcast system");
  }

  @GetMapping("/success")
  public String testSuccess() {
    return "success";
  }
}

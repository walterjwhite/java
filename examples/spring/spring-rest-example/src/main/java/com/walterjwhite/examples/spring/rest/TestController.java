package com.walterjwhite.examples.spring.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {




  @GetMapping("/throw")
  public String testThrow() {

    throw new IllegalStateException("This is a test of the emergency broadcast system");
  }

  @GetMapping("/success")
  public String testSuccess() {
    return "success";
  }
}

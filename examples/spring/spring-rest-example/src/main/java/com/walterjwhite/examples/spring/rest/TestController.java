package com.walterjwhite.examples.spring.rest;

import com.walterjwhite.examples.spring.rest.widget.data.Assembly;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/assemblies")
    public List<Assembly> getassemblies() {
        return List.of(new Assembly());
    }
}

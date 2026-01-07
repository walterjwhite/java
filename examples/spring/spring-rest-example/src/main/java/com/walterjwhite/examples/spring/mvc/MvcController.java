package com.walterjwhite.examples.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {
  @GetMapping("/async")
  public String async() {
    return "async";
  }

  @GetMapping("/batch")
  public String batch() {
    return "batch";
  }

  @GetMapping("/chat")
  public String chat() {
    return "chat";
  }

  @GetMapping("/batch_history")
  public String batchHistory() {
    return "batch_history";
  }

  @GetMapping("/batch_simple")
  public String batchSimple() {
    return "batch_simple";
  }

  @GetMapping("/step_history")
  public String stepHistory() {
    return "step_history";
  }
}

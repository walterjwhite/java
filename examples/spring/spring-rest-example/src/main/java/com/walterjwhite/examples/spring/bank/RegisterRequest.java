package com.walterjwhite.examples.spring.bank;

import lombok.Data;

@Data
public class RegisterRequest {
  private String username;
  private String password;
}

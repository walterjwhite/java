package com.walterjwhite.examples.cli.jersey;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class ServletTest {
  @Test
  void testHi() {
    RestAssured.registerParser("text/plain", Parser.TEXT);
    RestAssured.get("/servlets/hi").then().body(Matchers.equalTo("HI\n")).statusCode(200);
  }

  @Test
  void testJaxRsHello() {
    RestAssured.get("/test-jaxrs/jersey/hello/fred")
        .then()
        .body("message", Matchers.equalTo("Hi fred"))
        .statusCode(200);
  }

  @Test
  void testJaxRsHelloJohn() {
    RestAssured.get("/test-jaxrs/jersey/hello/John")
        .then()
        .body("message", Matchers.equalTo("Hi John"))
        .statusCode(200);
  }
}

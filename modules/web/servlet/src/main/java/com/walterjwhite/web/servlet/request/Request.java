package com.walterjwhite.web.servlet.request;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
  private Long id;
  private String ip;
  private Instant timestamp;
  private String uri;
  private String userAgent;
}

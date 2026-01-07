package com.walterjwhite.web.servlet.cidr_filter;

import com.walterjwhite.ip.cidr.CIDRMatcher;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CIDRRule {
  protected final CIDRMatcher cidrMatcher;
  protected final RoutingAction routingAction;
  protected final String target;
}

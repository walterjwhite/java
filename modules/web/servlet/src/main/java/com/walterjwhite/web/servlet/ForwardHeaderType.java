package com.walterjwhite.web.servlet;

import com.walterjwhite.ip.util.IPAddressUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ForwardHeaderType {
  X_FORWARDED_FOR("X-Forwarded-For"),
  FORWARDED("Forwarded") {
    public String getIPFromMultiValue(final String headerValue) {
      List<String> candidates = parseForwardHeader(headerValue);
      return IPAddressUtil.firstPublicOrNonLocal(candidates);
    }

    private static List<String> parseForwardHeader(final String headerValue) {
      List<String> results = new ArrayList<>();
      for (String part : headerValue.split(",")) {
        String[] kvs = part.split(";");
        for (String kv : kvs) {
          kv = kv.trim();
          if (kv.toLowerCase(Locale.ROOT).startsWith("for=")) {
            String val = kv.substring(4).trim();
            if (val.startsWith("\"") && val.endsWith("\"") && val.length() >= 2) {
              val = val.substring(1, val.length() - 1);
            }
            results.add(val);
          }
        }
      }
      return results;
    }
  },
  X_REAL_IP("X-Real-IP"),
  CF_CONNECTING_IP("CF-Connecting-IP"),
  TRUE_CLIENT_IP("True-Client-IP"),
  FASTLY_CLIENT_IP("Fastly-Client-Ip"),
  X_CLIENT_IP("X-Client-IP"),
  X_CLUSTER_CLIENT_IP("X-Cluster-Client-IP");

  private final String headerName;

  public String getIPFromMultiValue(final String headerValue) {
    List<String> candidates =
        Arrays.stream(headerValue.split(",")).map(String::trim).collect(Collectors.toList());
    return IPAddressUtil.firstPublicOrNonLocal(candidates);
  }

  public String getIPFromSingleValue(final String headerValue) {
    final String cleaned = IPAddressUtil.cleanCandidate(headerValue);
    if (IPAddressUtil.isPublicAddress(cleaned)) {
      return cleaned;
    }

    return null;
  }

  public static ForwardHeaderType fromHeaderName(final String headerName) {
    for (ForwardHeaderType forwardHeaderType : values()) {
      if (forwardHeaderType.name().equalsIgnoreCase(headerName)) {
        return forwardHeaderType;
      }
    }

    throw new IllegalArgumentException("header not matched: " + headerName);
  }
}

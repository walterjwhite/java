package com.walterjwhite.web.servlet;

import com.walterjwhite.ip.util.IPAddressUtil;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestHeaderUtil {
  public static String getClientIP(ServletRequest req, Set<String> trustedProxies) {
    String remoteAddr = req.getRemoteAddr();
    boolean remoteIsTrusted = trustedProxies != null && trustedProxies.contains(remoteAddr);

    if (!(req instanceof HttpServletRequest)) {
      return remoteAddr;
    }
    HttpServletRequest hreq = (HttpServletRequest) req;

    if (!remoteIsTrusted) {
      for (ForwardHeaderType forwardHeaderType : ForwardHeaderType.values()) {
        final String headerValue = hreq.getHeader(forwardHeaderType.name());
        if (headerValue == null || headerValue.isBlank()) {
          continue;
        }

        final String IP = forwardHeaderType.getIPFromMultiValue(headerValue);
        if (IP != null) {
          return IP;
        }
      }

      for (ForwardHeaderType forwardHeaderType : ForwardHeaderType.values()) {
        final String headerValue = hreq.getHeader(forwardHeaderType.name());
        if (headerValue == null || headerValue.isBlank()) {
          continue;
        }

        final String IP = forwardHeaderType.getIPFromSingleValue(headerValue);
        if (IP != null) {
          return IP;
        }
      }
    }

    return remoteAddr;
  }

  public static String getClientIp(final HttpServletRequest httpServletRequest) {
    final String xff = httpServletRequest.getHeader("X-Forwarded-For");
    if (xff != null && !xff.isBlank()) {
      String first = xff.split(",")[0].trim();
      return IPAddressUtil.normalizeIp(first);
    }

    final String realIp = httpServletRequest.getHeader("X-Real-IP");
    if (realIp != null && !realIp.isBlank()) {
      return realIp;
    }

    return IPAddressUtil.normalizeIp(httpServletRequest.getRemoteAddr());
  }
}

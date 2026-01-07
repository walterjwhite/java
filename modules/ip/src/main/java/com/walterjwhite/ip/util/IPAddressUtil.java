package com.walterjwhite.ip.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IPAddressUtil {
  public static String firstPublicOrNonLocal(List<String> candidates) {
    for (String c : candidates) {
      String cleaned = cleanCandidate(c);
      if (cleaned.isEmpty()) continue;
      if (isPublicAddress(cleaned)) return cleaned;
    }
    for (String c : candidates) {
      String cleaned = cleanCandidate(c);
      if (!cleaned.isEmpty()) return cleaned;
    }
    return null;
  }

  public static String cleanCandidate(String s) {
    if (s == null) return "";
    String t = s.trim();
    if (t.startsWith("[")) {
      int end = t.indexOf(']');
      if (end > 0) {
        t = t.substring(1, end);
      }
    } else {
      int colonCount = t.length() - t.replace(":", "").length();
      if (colonCount == 1 && t.contains(".")) {
        t = t.split(":", 2)[0];
      }
      if (t.startsWith("\"") && t.endsWith("\"") && t.length() >= 2) {
        t = t.substring(1, t.length() - 1);
      }
    }

    return t;
  }

  public static boolean isPublicAddress(String ip) {
    try {
      InetAddress ia = InetAddress.getByName(ip);
      if (ia.isAnyLocalAddress()
          || ia.isLoopbackAddress()
          || ia.isLinkLocalAddress()
          || ia.isSiteLocalAddress()
          || ia.isMulticastAddress()) {
        return false;
      }
      if (ip.startsWith("fc")
          || ip.startsWith("FD")
          || ip.startsWith("fd")
          || ip.startsWith("FC")) {
        return false;
      }

      return true;
    } catch (UnknownHostException e) {
      return false;
    }
  }

  public static String normalizeIp(String ip) {
    if (ip == null) {
      return null;
    }

    try {
      InetAddress addr = InetAddress.getByName(ip);
      if (addr instanceof java.net.Inet6Address) {
        String hostAddr = addr.getHostAddress(); // may be "::1" or "::ffff:127.0.0.1"
        if (addr.isLoopbackAddress()) {
          return "127.0.0.1";
        }

        return hostAddr;
      }

      return addr.getHostAddress();
    } catch (UnknownHostException e) {
      return ip;
    }
  }
}

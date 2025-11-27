package com.walterjwhite.examples.spring.util;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IPUtil {
    private static final List<String> FORWARD_HEADERS = List.of(
            "X-Forwarded-For", "Forwarded", "X-Real-IP",
            "CF-Connecting-IP", "True-Client-IP", "Fastly-Client-Ip",
            "X-Client-IP", "X-Cluster-Client-IP"
    );

    public static String getClientIp(ServletRequest req, Set<String> trustedProxies) {
        String remoteAddr = req.getRemoteAddr();
        boolean remoteIsTrusted = trustedProxies != null && trustedProxies.contains(remoteAddr);

        if (!(req instanceof HttpServletRequest)) {
            return remoteAddr;
        }
        HttpServletRequest hreq = (HttpServletRequest) req;

        if (remoteIsTrusted) {
            for (String header : FORWARD_HEADERS) {
                String hdr = hreq.getHeader(header);
                if (hdr == null || hdr.isBlank()) continue;

                if ("Forwarded".equalsIgnoreCase(header)) {
                    List<String> candidates = parseForwardHeader(hdr);
                    String ip = firstPublicOrNonLocal(candidates);
                    if (ip != null) return ip;
                } else {
                    List<String> candidates = Arrays.stream(hdr.split(","))
                            .map(String::trim)
                            .collect(Collectors.toList());
                    String ip = firstPublicOrNonLocal(candidates);
                    if (ip != null) return ip;
                }
            }
            for (String header : FORWARD_HEADERS) {
                String val = hreq.getHeader(header);
                if (val != null && !val.isBlank()) {
                    String cleaned = cleanCandidate(val);
                    if (isPublicAddress(cleaned)) return cleaned;
                }
            }
        }

        return remoteAddr;
    }

    private static List<String> parseForwardHeader(String hdr) {
        List<String> results = new ArrayList<>();
        for (String part : hdr.split(",")) {
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

    private static String firstPublicOrNonLocal(List<String> candidates) {
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

    private static String cleanCandidate(String s) {
        if (s == null) return "";
        String t = s.trim();
        if (t.startsWith("[")) {
            int end = t.indexOf(']');
            if (end > 0) t = t.substring(1, end);
        } else {
            int colonCount = t.length() - t.replace(":", "").length();
            if (colonCount == 1 && t.contains(".")) {
                t = t.split(":", 2)[0];
            }
            if (t.startsWith("\"") && t.endsWith("\"") && t.length() >= 2) t = t.substring(1, t.length() - 1);
        }
        return t;
    }

    private static boolean isPublicAddress(String ip) {
        try {
            InetAddress ia = InetAddress.getByName(ip);
            if (ia.isAnyLocalAddress() || ia.isLoopbackAddress() || ia.isLinkLocalAddress()
                    || ia.isSiteLocalAddress() || ia.isMulticastAddress()) {
                return false;
            }
            if (ip.startsWith("fc") || ip.startsWith("FD") || ip.startsWith("fd") || ip.startsWith("FC")) {
                return false;
            }
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }
}

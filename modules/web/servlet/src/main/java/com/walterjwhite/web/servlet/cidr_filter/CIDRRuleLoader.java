package com.walterjwhite.web.servlet.cidr_filter;

import com.walterjwhite.ip.cidr.CIDRMatcher;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CIDRRuleLoader {
  public static void load(final String rulesLocation, final List<CIDRRule> rules) {
    try (final InputStream inputStream = CIDRRuleLoader.class.getResourceAsStream(rulesLocation)) {
      loadProperties(rules, inputStream);
    } catch (Exception e) {
      LOGGER.error("Failed to initialize CidrRoutingFilter: ", e);
    }
  }

  private static void loadProperties(final List<CIDRRule> rules, final InputStream in)
      throws IOException {
    Properties props = new Properties();
    props.load(in);
    SortedMap<Integer, String> ordered = new TreeMap<>();
    for (String name : props.stringPropertyNames()) {
      if (name.startsWith("rule.")) {
        try {
          int idx = Integer.parseInt(name.substring("rule.".length()));
          ordered.put(idx, props.getProperty(name));
        } catch (NumberFormatException e) {
          LOGGER.warn("ignoring line: ", e);
        }
      }
    }
    for (Map.Entry<Integer, String> e : ordered.entrySet()) {
      CIDRRule r = parseRuleLine(e.getValue());
      if (r != null) {
        rules.add(r);
        LOGGER.info("Loaded rule: {}", r);
      }
    }
  }

  private static CIDRRule parseRuleLine(String line) {
    if (line == null) return null;
    String[] parts = line.split(",", 3);
    if (parts.length < 2) {
      LOGGER.warn("Invalid rule line (must contain at least CIDR and ACTION): {}", line);
      return null;
    }
    String cidr = parts[0].trim();
    String actionRaw = parts[1].trim().toUpperCase(Locale.ROOT);
    RoutingAction action;
    try {
      action = RoutingAction.valueOf(actionRaw);
    } catch (IllegalArgumentException ex) {
      LOGGER.warn("Unknown action in rule: {}", actionRaw);
      return null;
    }
    String target = (parts.length >= 3) ? parts[2].trim() : null;
    try {
      CIDRMatcher matcher = CIDRMatcher.toCIDRMatcher(cidr);
      return new CIDRRule(matcher, action, target);
    } catch (Exception ex) {
      LOGGER.warn("Failed to parse CIDR in rule: {}", cidr, ex);
      return null;
    }
  }
}

package com.walterjwhite.ip.impl.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLStreamUtil {
  public static String getResponse(final String uri) throws Exception {
    final URLConnection urlConnection = new URL(uri).openConnection();
    urlConnection.setDoOutput(true);
    urlConnection.setAllowUserInteraction(false);

    final StringBuilder buffer = new StringBuilder();
    try (final BufferedReader br =
        new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
      String l;
      while ((l = br.readLine()) != null) {
        buffer.append(l);
      }

      return (buffer.toString());
    }
  }
}

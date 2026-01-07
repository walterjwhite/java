package com.walterjwhite.ip.impl;

import com.walterjwhite.ip.api.service.PublicIPLookupService;
import com.walterjwhite.ip.util.URLStreamUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

@Getter
@RequiredArgsConstructor
public enum PublicIPAddressLookupProvider implements PublicIPLookupService {
  APIIPIFY("https://api.ipify.org?format=json", "ip"),
  HTTPBIN("http://httpbin.org/ip", "origin"),
  JSONIP("https://jsonip.com", "ip");

  private final String uri;
  private final String fieldName;

  public String getPublicIPAddress() throws Exception {
    final String response = URLStreamUtil.getResponse(uri);
    final JSONObject jsonObject = new JSONObject(response);

    return jsonObject.getString(fieldName);
  }
}

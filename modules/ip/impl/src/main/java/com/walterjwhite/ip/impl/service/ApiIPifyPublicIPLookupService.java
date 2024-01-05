package com.walterjwhite.ip.impl.service;

import com.walterjwhite.ip.api.service.PublicIPLookupService;
import com.walterjwhite.ip.impl.util.URLStreamUtil;
import org.json.JSONObject;

public class ApiIPifyPublicIPLookupService implements PublicIPLookupService {
  public String getPublicIPAddress() throws Exception {
    final String response = URLStreamUtil.getResponse("https://api.ipify.org?format=json");
    final JSONObject jsonObject = new JSONObject(response);
    final String ip = jsonObject.getString("ip");

    return (ip);
  }
}

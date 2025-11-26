package com.walterjwhite.ip.impl.service;

import com.walterjwhite.ip.api.service.PublicIPLookupService;
import com.walterjwhite.ip.impl.util.URLStreamUtil;
import org.json.JSONObject;

public class JSONIPPublicIPLookupService implements PublicIPLookupService {
  public String getPublicIPAddress() throws Exception {

    final String response = getStringResponse();

    final JSONObject jsonObject = new JSONObject(response);
    final String ip = jsonObject.getString("ip");

    return (ip);
  }

  protected String getStringResponse() throws Exception {
    return URLStreamUtil.getResponse("https://jsonip.com");
  }
}

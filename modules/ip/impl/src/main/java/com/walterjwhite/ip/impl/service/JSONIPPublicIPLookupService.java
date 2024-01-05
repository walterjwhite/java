package com.walterjwhite.ip.impl.service;

import com.walterjwhite.ip.api.service.PublicIPLookupService;
import com.walterjwhite.ip.impl.util.URLStreamUtil;
import org.json.JSONObject;

/**
 * TODO: automatically set the proxy for all of these providers perhaps configure it through a
 * properties file.
 */
public class JSONIPPublicIPLookupService implements PublicIPLookupService {
  public String getPublicIPAddress() throws Exception {
    // return json.load(StringIO(urlopen('https://jsonip.com').read().decode('utf-8')))['ip']

    final String response = getStringResponse();

    final JSONObject jsonObject = new JSONObject(response);
    final String ip = jsonObject.getString("ip");

    return (ip);
  }

  protected String getStringResponse() throws Exception {
    return URLStreamUtil.getResponse("https://jsonip.com");
  }
}

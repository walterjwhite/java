package com.walterjwhite.citrix;

import lombok.Data;

@Data
public class CitrixCredentials {
    protected String username;
    protected String password;
    protected String token;

    protected transient String ephemeralToken;
}

package com.walterjwhite.citrix;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CitrixSession {
    protected long backgroundJavascriptTimeout;
    protected long pageLoadTimeout;
    protected long loginTimeout;
    protected String url;
    protected CitrixCredentials citrixCredentials;

    protected transient final Map<Class, Object> contextMap = new HashMap<>();

    public <Type> void setContextValue(final Class<Type> clazz, final Type t) {
        contextMap.put(clazz, t);
    }

    public <Type> Type getContextValue(final Class<Type> clazz) {
        return (Type)contextMap.get(clazz);
    }
}

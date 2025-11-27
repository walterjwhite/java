package com.walterjwhite.metrics.enumeration;

import java.lang.reflect.Method;

public enum MetricsType {
    Counter,
    Gauge,
    Histogram{
        @Override
        public boolean isSupported(final Method method) {
            if(Double.class.equals(method.getReturnType())) {
                return true;
            }
            if(Integer.class.equals(method.getReturnType())) {
                return true;
            }

            return false;
        }
    },
    Summary,
    Timer;

    public boolean isSupported(final Method method) {
        return true;
    }
}

package com.walterjwhite.examples.spring.limit;

import lombok.Getter;

@Getter
public class TokenBucket {
    private final double capacity;
    private final double refillPerMillis;
    private double tokens;
    private long lastRefillMillis;

    public TokenBucket(double capacity, double refillPerSecond) {
        this.capacity = capacity;
        this.refillPerMillis = refillPerSecond / 1000.0;
        this.tokens = capacity;
        this.lastRefillMillis = System.currentTimeMillis();
    }

    private void refill() {
        long now = System.currentTimeMillis();
        if (now <= lastRefillMillis) return;
        double toAdd = (now - lastRefillMillis) * refillPerMillis;
        tokens = Math.min(capacity, tokens + toAdd);
        lastRefillMillis = now;
    }

    public synchronized boolean tryConsume(int amount) {
        refill();
        if (tokens >= amount) {
            tokens -= amount;
            return true;
        }
        return false;
    }
}
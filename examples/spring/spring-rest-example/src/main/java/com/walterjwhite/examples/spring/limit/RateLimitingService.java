package com.walterjwhite.examples.spring.limit;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class RateLimitingService {

    private static final double IDENTITY_REFILL_PER_SEC = 1.0;
    private static final double IDENTITY_CAPACITY = 2.0;

    private static final double GLOBAL_REFILL_PER_SEC = 2.0;
    private static final double GLOBAL_CAPACITY = 5.0;

    private final ConcurrentMap<String, TokenBucket> buckets = new ConcurrentHashMap<>();
    private final TokenBucket globalBucket = new TokenBucket(GLOBAL_CAPACITY, GLOBAL_REFILL_PER_SEC);

    public boolean tryConsumeForIdentity(String identity) {
        TokenBucket tb = buckets.computeIfAbsent(identity, k -> new TokenBucket(IDENTITY_CAPACITY, IDENTITY_REFILL_PER_SEC));
        return tb.tryConsume(1);
    }

    public boolean tryConsumeGlobal() {
        return globalBucket.tryConsume(1);
    }

    public double getGlobalTokens() { return globalBucket.getTokens(); }

    public double getTokensForIdentity(String identity) {
        TokenBucket tb = buckets.get(identity);
        return tb == null ? IDENTITY_CAPACITY : tb.getTokens();
    }
}
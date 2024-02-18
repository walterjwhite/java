package com.walterjwhite.keep_alive;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeepAliveHelper {
    private static final ScheduledExecutorService EXECUTOR_SERVICE =
            Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    public static <Type extends KeepAlive> void keepAlive(
            final KeepAliveable<Type> keepAliveable, final Type data)
            throws ExecutionException, InterruptedException {
        if (data.getKeepAliveDuration() == null || data.getKeepAliveInterval() == null) {
            return;
        }

        final ScheduledFuture keepAliveFuture = getKeepAliveFuture(keepAliveable, data);
        final ScheduledFuture durationFuture = getDurationFuture(data, keepAliveFuture);

        try {

            keepAliveFuture.get();
        } finally {
            durationFuture.cancel(true);
        }
    }

    private static <Type extends KeepAlive> ScheduledFuture getKeepAliveFuture(
            final KeepAliveable<Type> keepAliveable, final Type data) {
        return EXECUTOR_SERVICE.scheduleWithFixedDelay(
                () -> keepAliveable.onKeepAlive(data),
                data.getKeepAliveInterval().toMillis(),
                data.getKeepAliveInterval().toMillis(),
                TimeUnit.MILLISECONDS);
    }

    private static <Type extends KeepAlive> ScheduledFuture getDurationFuture(
            final Type data, final ScheduledFuture keepAliveFuture) {
        return EXECUTOR_SERVICE.schedule(
                () -> {
                    keepAliveFuture.cancel(true);
                },
                data.getKeepAliveDuration().toMillis(),
                TimeUnit.MILLISECONDS);
    }
}

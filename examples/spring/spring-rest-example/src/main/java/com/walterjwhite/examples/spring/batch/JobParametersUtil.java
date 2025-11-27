package com.walterjwhite.examples.spring.batch;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JobParametersUtil {
    public static JobParameters toJobParameters(Map<String, String> params, boolean includeTimestamp) {
        JobParametersBuilder builder = new JobParametersBuilder();
        if (params != null) {
            for (Map.Entry<String, String> e : params.entrySet()) {
                String key = e.getKey();
                String value = e.getValue();
                if (value == null) continue;
                if (looksLikeLong(value)) {
                    try {
                        builder.addLong(key, Long.parseLong(value));
                        continue;
                    } catch (NumberFormatException ignored) {
                    }
                }
                if (looksLikeDouble(value)) {
                    try {
                        builder.addDouble(key, Double.parseDouble(value));
                        continue;
                    } catch (NumberFormatException ignored) {
                    }
                }
                try {
                    Instant inst = Instant.parse(value);
                    builder.addDate(key, Date.from(inst));
                    continue;
                } catch (DateTimeParseException ignored) {
                }
                builder.addString(key, value);
            }
        }

        if (includeTimestamp) {
            if (params == null || !params.containsKey("ts")) {
                builder.addLong("ts", System.currentTimeMillis());
            }
        }

        return builder.toJobParameters();
    }

    public JobParameters toJobParameters(Map<String, String> params) {
        return toJobParameters(params, true);
    }

    private static boolean looksLikeLong(String s) {
        return s.matches("[-+]?\\d+");
    }

    private static boolean looksLikeDouble(String s) {
        return s.matches("[-+]?(\\d*\\.\\d+|\\d+\\.\\d*)([eE][-+]?\\d+)?") || s.matches("[-+]?\\d+[eE][-+]?\\d+");
    }
}

package com.walterjwhite.metrics.enumeration;

import com.walterjwhite.metrics.advice.*;
import io.micrometer.core.instrument.*;
import io.micrometer.core.instrument.search.Search;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MetricType {
  Counter(MetricsType.Counter, CounterAdvice.class) {
    @Override
    public Counter get(Search search) {
      return search.counter();
    }

    @Override
    public Meter build(
        final String meterName, final MeterRegistry meterRegistry, final String... additionalTags) {
      final Counter.Builder builder = io.micrometer.core.instrument.Counter.builder(meterName);

      if (additionalTags != null && additionalTags.length > 0) {
        builder.tags(additionalTags);
      }

      return builder.register(meterRegistry);
    }
  },
  Gauge(MetricsType.Gauge, GaugeAdvice.class) {
    @Override
    public Gauge get(Search search) {
      return search.gauge();
    }

    @Override
    public Meter build(
        final String meterName, final MeterRegistry meterRegistry, final String... additionalTags) {
      throw new UnsupportedOperationException("Not implemented.");
    }
  },
  Histogram(MetricsType.Histogram, HistogramAdvice.class) {
    @Override
    public Meter get(Search search) {
      return search.summary();
    }

    @Override
    public Meter build(
        final String meterName, final MeterRegistry meterRegistry, final String... additionalTags) {
      throw new UnsupportedOperationException("Not implemented.");
    }
  },
  Summary(MetricsType.Summary, SummaryAdvice.class) {
    @Override
    public DistributionSummary get(Search search) {
      return search.summary();
    }

    @Override
    public Meter build(
        final String meterName, final MeterRegistry meterRegistry, final String... additionalTags) {
      final DistributionSummary.Builder builder =
          io.micrometer.core.instrument.DistributionSummary.builder(meterName);

      if (additionalTags != null && additionalTags.length > 0) {
        builder.tags(additionalTags);
      }

      return builder.register(meterRegistry);
    }
  },
  Timer(MetricsType.Timer, TimerAdvice.class) {
    @Override
    public Timer get(Search search) {
      return search.timer();
    }

    @Override
    public Meter build(
        final String meterName, MeterRegistry meterRegistry, final String... additionalTags) {
      final Timer.Builder builder = io.micrometer.core.instrument.Timer.builder(meterName);

      if (additionalTags != null && additionalTags.length > 0) {
        builder.tags(additionalTags);
      }

      return builder.register(meterRegistry);
    }
  };

  private final MetricsType metricsType;
  private final Class adviceClass;

  public abstract Meter get(Search search);

  public abstract Meter build(
      final String meterName, MeterRegistry meterRegistry, final String... additionalTags);

  public static MetricType get(final MetricsType metricsType) {
    return Arrays.stream(values())
        .filter(metricType -> metricType.getMetricsType().equals(metricsType))
        .findFirst()
        .get();
  }
}

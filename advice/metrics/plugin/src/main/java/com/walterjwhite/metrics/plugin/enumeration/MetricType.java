package com.walterjwhite.metrics.plugin.enumeration;

import com.walterjwhite.metrics.annotation.CounterConfiguration;
import com.walterjwhite.metrics.annotation.GaugeConfiguration;
import com.walterjwhite.metrics.annotation.SummaryConfiguration;
import com.walterjwhite.metrics.annotation.TimerConfiguration;
import io.micrometer.core.instrument.*;
import io.micrometer.core.instrument.search.Search;

import java.lang.annotation.Annotation;



public enum MetricType {
    Counter(CounterConfiguration.class) {
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
    Gauge(GaugeConfiguration.class) {
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
    Histogram(com.walterjwhite.metrics.annotation.Histogram.class) {
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
    Summary(SummaryConfiguration.class) {
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
    Timer(TimerConfiguration.class) {
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

    private final Class<? extends Annotation> metricAnnotationClass;

    MetricType(Class<? extends Annotation> metricAnnotationClass) {
        this.metricAnnotationClass = metricAnnotationClass;
    }

    public Class<? extends Annotation> getMetricAnnotationClass() {
        return metricAnnotationClass;
    }

    public abstract Meter get(Search search);

    public abstract Meter build(
            final String meterName, MeterRegistry meterRegistry, final String... additionalTags);
}

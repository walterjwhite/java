package com.walterjwhite.metrics.util;

import com.walterjwhite.metrics.TagParameter;
import com.walterjwhite.metrics.enumeration.MetricType;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.search.Search;
import java.util.Arrays;

public class MetricsRegistryHelper {
  public static final String METER_NAME_TEMPLATE = "%s.%s.%s";

  public static MeterRegistry METER_REGISTRY;
  public static boolean NO_OPERATION = true;

  private MetricsRegistryHelper() {}

  public static void setMeterRegistry(final MeterRegistry meterRegistry) {
    METER_REGISTRY = meterRegistry;
    NO_OPERATION = false;
  }

  public static boolean isNoOperation() {
    return NO_OPERATION;
  }

  public static <MeterType extends Meter> MeterType get(
      final Object interecepted, final MetricType metricType, final String... additionalTags) {
    final String meterName = getMeterName(interecepted, metricType);

    final Search search = search(meterName, additionalTags);
    final MeterType meter = (MeterType) metricType.get(search);
    if (meter != null) {
      return meter;
    }

    return (MeterType) metricType.build(meterName, METER_REGISTRY, additionalTags);
  }

  private static String getMeterName(final Object intercepted, final MetricType metricType) {
    return String.format(
        METER_NAME_TEMPLATE,
        intercepted.getClass().getName(),
        intercepted.getClass().getName(),
        metricType.name());
  }

  private static Search search(final String meterName, final String... additionalTags) {
    if (additionalTags != null && additionalTags.length > 0) {
      return METER_REGISTRY.find(meterName).tags(additionalTags);
    }

    return METER_REGISTRY.find(meterName);
  }

  public static String[] getTagParametersAsString(final TagParameter... tagParameters) {
    final String[] tagParametersString = new String[tagParameters.length * 2];
    int i = 0;
    Arrays.stream(tagParameters).forEach(tagParameter -> {});

    for (final TagParameter tagParameter : tagParameters) {
      tagParametersString[i++] = tagParameter.getMeterAttribute().name();
      tagParametersString[i++] = tagParameter.getArgument().toString();
    }

    return tagParametersString;
  }
}

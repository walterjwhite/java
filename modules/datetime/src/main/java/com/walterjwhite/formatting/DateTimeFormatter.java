package com.walterjwhite.formatting;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class DateTimeFormatter {
  public static final int DEFAULT_PRECISION = 2;
  public static final int DAYS_PER_YEAR = 365;
  public static final int DAYS_PER_MONTH = 30;
  public static final int DAYS_PER_WEEK = 7;

  public static String toString(Duration duration) {
    return (toString(duration, DEFAULT_PRECISION));
  }

  /**
   * Format a duration using the specified number of precision. ie. if precision is 2, then the
   * output would be something like 1 day 1 hour
   *
   * @param duration
   * @param precision
   * @return
   */
  public static String toString(Duration duration, int precision) {
    final long days = duration.toDays();

    if (days >= DAYS_PER_YEAR) {
      return yearFormat(duration, precision, days);
    }
    if (days >= DAYS_PER_MONTH) {
      return monthFormat(duration, precision, days);
    }
    if (days >= DAYS_PER_WEEK) {
      return weekFormat(duration, precision, days);
    }
    if (days >= 1) {
      return dayFormat(duration, precision, days);
    }

    final long hours = duration.toHours();
    if (hours >= 1) {
      return hourFormat(duration, precision, hours);
    }

    final long minutes = duration.toMinutes();
    if (minutes >= 1) {
      return minuteFormat(duration, precision, minutes);
    }

    final long seconds = duration.getSeconds();
    if (seconds >= 1) {
      return secondFormat(duration, precision, seconds);
    }

    final int nanoseconds = duration.getNano();
    if (nanoseconds >= 1) return format(nanoseconds, "nanosecond");

    return null;
  }

  private static String format(final long number, final String suffix) {
    if (number > 1) return number + " " + suffix + "s";

    return number + " " + suffix;
  }

  private static String join(final String left, final String right) {
    return join(left, ", ", right);
  }

  private static String join(final String left, final String separator, final String right) {
    if (right == null) return left;

    return left + separator + right;
  }

  private static String yearFormat(final Duration duration, final int precision, final long days) {
    final long years = days / DAYS_PER_YEAR;
    if (precision > 1)
      return join(
          format(years, "year"), toString(duration.minus(years, ChronoUnit.YEARS), precision - 1));

    return format(years, "year");
  }

  private static String monthFormat(final Duration duration, final int precision, final long days) {
    final long months = days / DAYS_PER_MONTH;
    if (precision > 1)
      return join(
          format(months, "month"),
          toString(duration.minus(months, ChronoUnit.MONTHS), precision - 1));

    return format(months, "month");
  }

  private static String weekFormat(final Duration duration, final int precision, final long days) {
    final long weeks = days / DAYS_PER_WEEK;
    if (precision > 1)
      return join(
          format(weeks, "week"), toString(duration.minus(weeks, ChronoUnit.WEEKS), precision - 1));

    return format(weeks, "week");
  }

  private static String dayFormat(final Duration duration, final int precision, final long days) {
    if (precision > 1)
      return join(format(days, "day"), toString(duration.minusDays(days), precision - 1));

    return format(days, "day");
  }

  private static String hourFormat(final Duration duration, final int precision, final long hours) {
    if (precision > 1)
      return join(format(hours, "hour"), toString(duration.minusHours(hours), precision - 1));

    return format(hours, "hour");
  }

  private static String minuteFormat(
      final Duration duration, final int precision, final long minutes) {
    if (precision > 1)
      return join(
          format(minutes, "minute"), toString(duration.minusMinutes(minutes), precision - 1));

    return format(minutes, "minute");
  }

  private static String secondFormat(
      final Duration duration, final int precision, final long seconds) {
    if (precision > 1)
      return join(
          format(seconds, "second"), toString(duration.minusSeconds(seconds), precision - 1));

    return format(seconds, "second");
  }
}

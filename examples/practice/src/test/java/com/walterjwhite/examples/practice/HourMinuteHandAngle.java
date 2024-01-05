package com.walterjwhite.examples.practice;

public class HourMinuteHandAngle {
  private HourMinuteHandAngle() {}

  public static int computeAngle(final int hour, final int minute) {
    if (hour < 0) {
      throw new IllegalArgumentException("Hour must be >= 0");
    }

    if (hour > 11) {
      throw new IllegalArgumentException("Hour must be <= 11");
    }

    if (minute < 0) {
      throw new IllegalArgumentException("Minute must be >= 0");
    }

    if (hour > 11) {
      throw new IllegalArgumentException("Minute must be <= 11");
    }

    // angle of hour
    // 12 hours / 360'
    // angle of minute
    // 60 minutes / 360'

    //        return 360*(hour/12) - 360*(minute/60);
    return (int) Math.round(360 * Math.abs(hour * 1.0 / 12 - minute * 1.0 / 60));
  }
}

package com.example.myothiha09.coursehelper.model;

/**
 * Created by Kevin on 1/3/2017.
 */
public class MeetingTime {
  //least sig = Mon, second least is Tue, etc.
  private int daysOfWeek;
  private Interval interval;

  public MeetingTime(int daysOfWeek, Time startTime, Time endTime) {
    this.daysOfWeek = daysOfWeek;
    this.interval = new Interval(startTime, endTime);
  }

  public MeetingTime(int daysOfWeek, Interval interval) {
    this.daysOfWeek = daysOfWeek;
    this.interval = interval;
  }

  public int getDaysOfWeek() {
    return daysOfWeek;
  }

  public Interval getInterval() {
    return interval;
  }

  public Time getStartTime() {
    return interval.getStartTime();
  }

  public Time getEndTime() {
    return interval.getEndTime();
  }

  public int getLength() {
    if (getStartTime().getHour() != getEndTime().getHour()) {
      int start = getStartTime().getHour() * 50 + getEndTime().getMinute();
      int end = getEndTime().getHour() * 50 + getEndTime().getMinute();
      return end - start;
    } else {
      return getEndTime().getMinute() - getStartTime().getMinute();
    }
  }

  public boolean conflictsWith(MeetingTime other) {
    if ((daysOfWeek & other.daysOfWeek) == 0) {
      return false;
    }
    if (interval.conflictsWith(other.interval)) {
      return true;
    } else {
      return false;
    }
  }

  public String toString() {
    StringBuilder builder = new StringBuilder();
    if ((daysOfWeek & 1) != 0) {
      builder.append("M/");
    }
    if ((daysOfWeek & 2) != 0) {
      builder.append("T/");
    }
    if ((daysOfWeek & 4) != 0) {
      builder.append("W/");
    }
    if ((daysOfWeek & 8) != 0) {
      builder.append("R/");
    }
    if ((daysOfWeek & 16) != 0) {
      builder.append("F/");
    }
    builder.deleteCharAt(builder.length() - 1);
    builder.append(" ");
    builder.append(interval.toString());
    return builder.toString();
  }
}


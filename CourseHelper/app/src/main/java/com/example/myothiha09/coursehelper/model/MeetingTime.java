package com.example.myothiha09.coursehelper.model;

/**
 * Created by Kevin on 1/3/2017.
 */
public class MeetingTime {
  //least sig = Mon, second least is Tue, etc.
  private int daysOfWeek;
  private Time startTime;
  private Time endTime;

  public MeetingTime(int daysOfWeek, Time startTime, Time endTime) {
    this.daysOfWeek = daysOfWeek;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public Time getStartTime() {
    return startTime;
  }

  public int getLength() {
    if (startTime.getHour() != endTime.getHour()) {
      int start = startTime.getHour() * 50 + startTime.getMinute();
      int end = endTime.getHour() * 50 + endTime.getMinute();
      return end - start;
    } else {
      return endTime.getMinute() - startTime.getMinute();
    }
  }

  public Time getEndTime() {
    return endTime;
  }

  public boolean conflictsWith(MeetingTime other) {
    if ((daysOfWeek & other.daysOfWeek) == 0) {
      return false;
    }
    if (this.endTime.compareTo(other.startTime) < 0) {
      return false;
    }
    if (other.endTime.compareTo(this.startTime) < 0) {
      return false;
    }
    return true;
  }

  public int getDaysOfWeek() {
    return daysOfWeek;
  }

  @Override public String toString() {
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
    builder.append(startTime.getHour());
    builder.append(":");
    if (startTime.getMinute() < 10) builder.append("0");
    builder.append(startTime.getMinute());
    builder.append("-");
    builder.append(endTime.getHour());
    builder.append(":");
    if (endTime.getMinute() < 10) builder.append("0");
    builder.append(endTime.getMinute());
    return builder.toString();
  }
}

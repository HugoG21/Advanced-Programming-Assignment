package com.campuscentral.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTime {

    private LocalDateTime dateTime;

    public DateTime() {
        this.dateTime = LocalDateTime.now();
    }

    public DateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public DateTime(int year, int month, int day, int hour, int minute, int second) {
        this.dateTime = LocalDateTime.of(year, month, day, hour, minute, second);
    }

    public DateTime(int year, int month, int day) {
        this.dateTime = LocalDateTime.of(year, month, day, 0, 0, 0);
    }

    public static DateTime now() {
        return new DateTime(LocalDateTime.now());
    }

    public String format() {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String formatDate() {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String formatTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public LocalDateTime toLocalDateTime() {
        return dateTime;
    }

    public LocalDate toLocalDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime toLocalTime() {
        return dateTime.toLocalTime();
    }

    public int getYear() { return dateTime.getYear(); }
    public int getMonth() { return dateTime.getMonthValue(); }
    public int getDay() { return dateTime.getDayOfMonth(); }
    public int getHour() { return dateTime.getHour(); }
    public int getMinute() { return dateTime.getMinute(); }
    public int getSecond() { return dateTime.getSecond(); }

    public DateTime plusDays(long days) { return new DateTime(dateTime.plusDays(days)); }
    public DateTime plusHours(long hours) { return new DateTime(dateTime.plusHours(hours)); }
    public DateTime plusMinutes(long minutes) { return new DateTime(dateTime.plusMinutes(minutes)); }

    public boolean isBefore(DateTime other) { return this.dateTime.isBefore(other.dateTime); }
    public boolean isAfter(DateTime other) { return this.dateTime.isAfter(other.dateTime); }

    public long daysUntil(DateTime other) { return java.time.temporal.ChronoUnit.DAYS.between(this.dateTime, other.dateTime); }
    public long hoursUntil(DateTime other) { return java.time.temporal.ChronoUnit.HOURS.between(this.dateTime, other.dateTime); }
    public long minutesUntil(DateTime other) { return java.time.temporal.ChronoUnit.MINUTES.between(this.dateTime, other.dateTime); }

    @Override
    public String toString() {
        return format();
    }
}
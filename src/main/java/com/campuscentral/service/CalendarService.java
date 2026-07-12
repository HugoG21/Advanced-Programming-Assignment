package com.campuscentral.service;

import com.campuscentral.model.CalendarEvent;
import com.campuscentral.model.Booking;
import com.campuscentral.util.DateTime;

import java.util.ArrayList;
import java.util.List;

public class CalendarService {
    private final List<CalendarEvent> events;

    public CalendarService() {
        this.events = new ArrayList<>();
    }

    public CalendarEvent createCalendarEvent(Booking booking) {
        CalendarEvent event = new CalendarEvent();
        event.setBookingId(booking.getBookingId());
        event.setUserId(booking.getUserId());
        event.setTitle("Booking: " + booking.getFacilityId());
        event.setEventDate(booking.getBookingDate());
        event.setStartTime(booking.getStartTime());
        event.setEndTime(booking.getEndTime());
        event.createEvent();
        events.add(event);
        return event;
    }

    public boolean updateCalendarEvent(String eventId) {
        return events.stream()
                .filter(e -> e.getEventId().equals(eventId))
                .findFirst()
                .map(CalendarEvent::updateEvent)
                .orElse(false);
    }

    public boolean deleteCalendarEvent(String eventId) {
        return events.removeIf(e -> e.getEventId().equals(eventId));
    }

    public boolean syncBookings(List<Booking> bookings) {
        for (Booking b : bookings) {
            createCalendarEvent(b);
        }
        return true;
    }

    public List<CalendarEvent> getCalendarEntries(String userId) {
        return events.stream().filter(e -> e.getUserId().equals(userId)).toList();
    }

    public String exportToGoogleCalendar(String eventId) {
        return events.stream()
                .filter(e -> e.getEventId().equals(eventId))
                .findFirst()
                .map(CalendarEvent::exportToGoogleCalendar)
                .orElse("Event not found");
    }

    public String exportToOutlookCalendar(String eventId) {
        return events.stream()
                .filter(e -> e.getEventId().equals(eventId))
                .findFirst()
                .map(CalendarEvent::exportToOutlookCalendar)
                .orElse("Event not found");
    }
}
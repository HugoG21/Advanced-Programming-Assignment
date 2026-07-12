package com.campuscentral.service;

import com.campuscentral.client.mcp.CampusMcpClient;
import com.campuscentral.model.Booking;
import com.campuscentral.util.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookingService {
    private final CampusMcpClient mcp;
    private final List<Booking> localBookings;

    public BookingService(CampusMcpClient mcp) {
        this.mcp = mcp;
        this.localBookings = new ArrayList<>();
    }

    public String checkAvailability(String date, String building) {
        return mcp.callTool("check_room_availability",
                Map.of("date", date, "building", building == null ? "" : building));
    }

    public String bookResource(String resourceId, String date, String startTime, String endTime, String studentId) {
        String ref = mcp.callTool("book_resource", Map.of(
                "resourceId", resourceId,
                "date", date,
                "startTime", startTime,
                "endTime", endTime,
                "studentId", studentId));
        Booking b = new Booking();
        b.setBookingReference(ref);
        b.setFacilityId(resourceId);
        b.setBookingDate(DateTime.fromDateString(date));
        b.setStartTime(startTime);
        b.setEndTime(endTime);
        b.setUserId(studentId);
        b.createBooking();
        localBookings.add(b);
        return ref;
    }

    public boolean cancelBooking(String bookingRef) {
        return localBookings.removeIf(b -> bookingRef.equals(b.getBookingReference()));
    }

    public List<Booking> getUserBookings(String userId) {
        return localBookings.stream().filter(b -> userId.equals(b.getUserId())).toList();
    }

    public String getBookingById(String ref) {
        return localBookings.stream()
                .filter(b -> ref.equals(b.getBookingReference()))
                .findFirst()
                .map(Booking::getBookingSummary)
                .orElse("Booking not found");
    }

    public String listLecturerSlots(String lecturerName, String day) {
        return mcp.callTool("list_lecturer_slots",
                Map.of("lecturerName", lecturerName, "day", day == null ? "" : day));
    }

    public String submitLeave(String studentId, String fromDate, String toDate, String reason) {
        return mcp.callTool("submit_leave_application", Map.of(
                "studentId", studentId,
                "fromDate", fromDate,
                "toDate", toDate,
                "reason", reason));
    }
}
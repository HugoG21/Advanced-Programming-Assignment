package com.campuscentral.data;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DataStore {
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private final Path dataDir;
    private final Path bookingsFile;
    private final Path usersFile;
    private final Path notificationsFile;
    private final AtomicInteger bookingSeq = new AtomicInteger(0);
    private final AtomicInteger userSeq = new AtomicInteger(0);

    public DataStore(Path dataDir) {
        this.dataDir = dataDir;
        this.bookingsFile = dataDir.resolve("bookings.txt");
        this.usersFile = dataDir.resolve("users.txt");
        this.notificationsFile = dataDir.resolve("notifications.txt");
        init();
    }

    private void init() {
        try {
            Files.createDirectories(dataDir);
            if (Files.notExists(bookingsFile))
                Files.writeString(bookingsFile, "# ref | resourceId | date | start | end | studentId | createdAt\n");
            if (Files.notExists(usersFile))
                Files.writeString(usersFile, "# userId | fullName | email | studentId | createdAt\n");
            if (Files.notExists(notificationsFile))
                Files.writeString(notificationsFile, "# notificationId | userId | title | type | sentAt\n");
            bookingSeq.set(countDataLines(bookingsFile));
            userSeq.set(countDataLines(usersFile));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public synchronized String addBooking(String resourceId, String date, String start, String end, String studentId) {
        String ref = "BK-" + (1000 + bookingSeq.incrementAndGet());
        String line = String.join(" | ", ref, resourceId, date, start, end, studentId, LocalDateTime.now().format(TS));
        append(bookingsFile, line);
        return ref;
    }

    public synchronized List<String> getBookings() { return readDataLines(bookingsFile); }
    public synchronized List<String> bookingsOnDate(String date) {
        List<String> out = new ArrayList<>();
        for (String line : readDataLines(bookingsFile)) {
            String[] parts = line.split("\\s*\\|\\s*");
            if (parts.length >= 3 && parts[2].equals(date)) out.add(line);
        }
        return out;
    }

    public synchronized void saveUser(String userId, String fullName, String email, String studentId) {
        String line = String.join(" | ", userId, fullName, email, studentId, LocalDateTime.now().format(TS));
        append(usersFile, line);
    }

    public synchronized List<String> getUsers() { return readDataLines(usersFile); }

    public synchronized void saveNotification(String notificationId, String userId, String title, String type) {
        String line = String.join(" | ", notificationId, userId, title, type, LocalDateTime.now().format(TS));
        append(notificationsFile, line);
    }

    private void append(Path file, String line) {
        try {
            Files.writeString(file, line + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) { throw new UncheckedIOException(e); }
    }

    private List<String> readDataLines(Path file) {
        try {
            List<String> lines = new ArrayList<>();
            for (String l : Files.readAllLines(file, StandardCharsets.UTF_8)) {
                if (!l.isBlank() && !l.startsWith("#")) lines.add(l);
            }
            return lines;
        } catch (IOException e) { throw new UncheckedIOException(e); }
    }

    private int countDataLines(Path file) { return readDataLines(file).size(); }
}
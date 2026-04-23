package flc.service;

import flc.model.*;

import java.util.*;

/**
 * Core service handling all booking operations for the FLC system.
 * Follows the Single Responsibility Principle — this class manages bookings only.
 */
public class BookingService {

    private final Map<String, Booking> bookings = new HashMap<>();
    private int bookingCounter = 1;

    /**
     * Books a member into a lesson.
     * Validates: space available, no time conflict, not already booked.
     *
     * @return the created Booking, or null if booking failed
     */
    public Booking bookLesson(Member member, Lesson lesson) {
        if (!lesson.hasSpace()) {
            System.out.println("  [BOOKING FAILED] Lesson is full: " + lesson);
            return null;
        }
        if (member.hasTimeConflict(lesson.getDay(), lesson.getTimeSlot(), lesson.getWeekNumber())) {
            System.out.println("  [BOOKING FAILED] Time conflict for " + member.getName());
            return null;
        }
        if (member.hasBookedLesson(lesson)) {
            System.out.println("  [BOOKING FAILED] Already booked: " + member.getName());
            return null;
        }

        String bookingId = "BK" + String.format("%04d", bookingCounter++);
        Booking booking = new Booking(bookingId, member, lesson);

        lesson.addMember(member);
        member.addBooking(booking);
        bookings.put(bookingId, booking);

        System.out.println("  [BOOKED] " + booking);
        return booking;
    }

    /**
     * Changes a member's existing booking from one lesson to another.
     * Validates that the new lesson has space and no time conflict.
     *
     * @return true if change was successful
     */
    public boolean changeBooking(Booking existingBooking, Lesson newLesson) {
        Member member = existingBooking.getMember();
        Lesson oldLesson = existingBooking.getLesson();

        if (!newLesson.hasSpace()) {
            System.out.println("  [CHANGE FAILED] New lesson is full.");
            return false;
        }

        // Temporarily remove the old booking to avoid false conflict detection
        oldLesson.removeMember(member);
        member.removeBooking(existingBooking);

        if (member.hasTimeConflict(newLesson.getDay(), newLesson.getTimeSlot(), newLesson.getWeekNumber())) {
            // Restore old booking
            oldLesson.addMember(member);
            member.addBooking(existingBooking);
            System.out.println("  [CHANGE FAILED] Time conflict with another booking.");
            return false;
        }

        // Apply the change
        newLesson.addMember(member);
        existingBooking.setLesson(newLesson);
        member.addBooking(existingBooking);

        System.out.println("  [CHANGED] " + member.getName()
                + " moved from [" + oldLesson.getExerciseType()
                + "] to [" + newLesson.getExerciseType() + "]");
        return true;
    }

    /**
     * Cancels a booking entirely.
     *
     * @return true if cancellation was successful
     */
    public boolean cancelBooking(Booking booking) {
        Member member = booking.getMember();
        Lesson lesson = booking.getLesson();

        lesson.removeMember(member);
        member.removeBooking(booking);
        bookings.remove(booking.getBookingId());

        System.out.println("  [CANCELLED] " + booking.getBookingId()
                + " for " + member.getName());
        return true;
    }

    /**
     * Finds a booking by its ID.
     */
    public Booking findBookingById(String bookingId) {
        return bookings.get(bookingId);
    }

    /**
     * Returns all bookings for a given member.
     */
    public List<Booking> getBookingsForMember(Member member) {
        List<Booking> result = new ArrayList<>();
        for (Booking b : bookings.values()) {
            if (b.getMember().equals(member)) {
                result.add(b);
            }
        }
        return result;
    }

    public Map<String, Booking> getAllBookings() {
        return Collections.unmodifiableMap(bookings);
    }
}

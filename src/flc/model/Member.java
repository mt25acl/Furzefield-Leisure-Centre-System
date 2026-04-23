package flc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a member of the Furzefield Leisure Centre.
 * A member can book lessons and write reviews.
 */
public class Member {

    private final String memberId;
    private final String name;
    private final String email;
    private final List<Booking> bookings;

    public Member(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.bookings = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    /**
     * Checks whether this member already has a booking at the same day and time slot,
     * which would create a time conflict.
     */
    public boolean hasTimeConflict(Day day, TimeSlot timeSlot, int weekNumber) {
        for (Booking b : bookings) {
            Lesson lesson = b.getLesson();
            if (lesson.getDay().equals(day)
                    && lesson.getTimeSlot().equals(timeSlot)
                    && lesson.getWeekNumber() == weekNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether this member has already booked a specific lesson.
     */
    public boolean hasBookedLesson(Lesson lesson) {
        for (Booking b : bookings) {
            if (b.getLesson().equals(lesson)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Member[%s] %s <%s>", memberId, name, email);
    }
}

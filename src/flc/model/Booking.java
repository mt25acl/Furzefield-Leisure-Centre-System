package flc.model;

/**
 * Represents a booking made by a member for a specific lesson.
 * Acts as a link between Member and Lesson.
 */
public class Booking {

    private final String bookingId;
    private final Member member;
    private Lesson lesson;
    private final long bookingTimestamp;

    public Booking(String bookingId, Member member, Lesson lesson) {
        this.bookingId = bookingId;
        this.member = member;
        this.lesson = lesson;
        this.bookingTimestamp = System.currentTimeMillis();
    }

    public String getBookingId() {
        return bookingId;
    }
    
    
    
    
    

    public Member getMember() {
        return member;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public long getBookingTimestamp() {
        return bookingTimestamp;
    }

    @Override
    public String toString() {
        return String.format("Booking[%s] %s -> %s", bookingId, member.getName(), lesson);
    }
}

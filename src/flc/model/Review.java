package flc.model;

/**
 * Represents a review written by a member after attending a lesson.
 * Rating is on a scale of 1 to 5.
 */
public class Review {

    public static final int MIN_RATING = 1;
    public static final int MAX_RATING = 5;

    private final String reviewId;
    private final Member member;
    private final Lesson lesson;
    private final int rating;
    private final String comment;

    public Review(String reviewId, Member member, Lesson lesson, int rating, String comment) {
        if (rating < MIN_RATING || rating > MAX_RATING) {
            throw new IllegalArgumentException(
                    "Rating must be between " + MIN_RATING + " and " + MAX_RATING);
        }
        this.reviewId = reviewId;
        this.member = member;
        this.lesson = lesson;
        this.rating = rating;
        this.comment = comment;
    }

    public String getReviewId() {
        return reviewId;
    }

    public Member getMember() {
        return member;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getRatingLabel() {
        switch (rating) {
            case 1: return "Very Dissatisfied";
            case 2: return "Dissatisfied";
            case 3: return "Ok";
            case 4: return "Satisfied";
            case 5: return "Very Satisfied";
            default: return "Unknown";
        }
    }

    @Override
    public String toString() {
        return String.format("Review[%s] by %s | Rating: %d (%s) | \"%s\"",
                reviewId, member.getName(), rating, getRatingLabel(), comment);
    }
}

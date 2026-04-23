package flc.service;

import flc.model.*;

import java.util.*;

/**
 * Handles the creation and retrieval of lesson reviews.
 * Only members who attended a lesson may leave a review.
 */
public class ReviewService {

    private final List<Review> allReviews = new ArrayList<>();
    private int reviewCounter = 1;

    /**
     * Adds a review from a member for a lesson they attended.
     *
     * @return the created Review, or null if the member did not attend the lesson
     */
    public Review addReview(Member member, Lesson lesson, int rating, String comment) {
        if (!lesson.isEnrolled(member)) {
            System.out.println("  [REVIEW FAILED] " + member.getName()
                    + " did not attend: " + lesson.getExerciseType());
            return null;
        }
        String reviewId = "RV" + String.format("%04d", reviewCounter++);
        Review review = new Review(reviewId, member, lesson, rating, comment);
        lesson.addReview(review);
        allReviews.add(review);
        System.out.println("  [REVIEW ADDED] " + review);
        return review;
    }

    /**
     * Returns all reviews across all lessons.
     */
    public List<Review> getAllReviews() {
        return Collections.unmodifiableList(allReviews);
    }

    /**
     * Returns all reviews for a specific lesson.
     */
    public List<Review> getReviewsForLesson(Lesson lesson) {
        return new ArrayList<>(lesson.getReviews());
    }
}

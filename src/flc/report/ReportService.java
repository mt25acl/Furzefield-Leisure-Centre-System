package flc.report;

import flc.model.*;
import flc.service.TimetableService;

import java.util.*;

/**
 * Generates the two mandatory reports required by the FLC system:
 *   1. Members per lesson per day with average rating.
 *   2. Highest income-generating exercise type.
 */
public class ReportService {

    private final TimetableService timetableService;

    public ReportService(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    /**
     * REPORT 1: Prints the number of members per lesson on each day, with average rating.
     * Covers all weeks in the timetable.
     */
    public void printMembersPerLessonReport() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("  REPORT 1: Members Per Lesson With Average Rating");
        System.out.println("=".repeat(70));

        List<Lesson> allLessons = timetableService.getAllLessons();
        allLessons.sort(Comparator.comparingInt(Lesson::getWeekNumber)
                .thenComparing(Lesson::getDay)
                .thenComparing(Lesson::getTimeSlot));

        int currentWeek = -1;
        Day currentDay = null;

        for (Lesson lesson : allLessons) {
            if (lesson.getWeekNumber() != currentWeek) {
                currentWeek = lesson.getWeekNumber();
                currentDay = null;
                System.out.println("\n  --- Week " + currentWeek + " ---");
            }
            if (!lesson.getDay().equals(currentDay)) {
                currentDay = lesson.getDay();
                System.out.println("  [" + currentDay + "]");
                System.out.printf("  %-12s %-18s %-8s %-8s %-14s%n",
                        "Time", "Exercise", "Members", "Spaces", "Avg Rating");
                System.out.println("  " + "-".repeat(62));
            }

            double avgRating = lesson.getAverageRating();
            String ratingStr = avgRating > 0
                    ? String.format("%.1f / 5.0", avgRating)
                    : "No reviews";

            System.out.printf("  %-12s %-18s %-8d %-8d %-14s%n",
                    lesson.getTimeSlot().getDisplayName(),
                    lesson.getExerciseType().toString(),
                    lesson.getEnrolledCount(),
                    Lesson.MAX_CAPACITY - lesson.getEnrolledCount(),
                    ratingStr);
        }
        System.out.println("\n" + "=".repeat(70));
    }

    /**
     * REPORT 2: Prints the exercise type that generated the highest total income
     * across all lessons of that type.
     */
    public void printHighestIncomeReport() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("  REPORT 2: Exercise Type with Highest Total Income");
        System.out.println("=".repeat(70));

        // Aggregate income per exercise type
        Map<ExerciseType, Double> incomeMap = new EnumMap<>(ExerciseType.class);
        Map<ExerciseType, Integer> bookingCountMap = new EnumMap<>(ExerciseType.class);

        for (Lesson lesson : timetableService.getAllLessons()) {
            ExerciseType type = lesson.getExerciseType();
            double income = lesson.getTotalIncome();
            incomeMap.merge(type, income, Double::sum);
            bookingCountMap.merge(type, lesson.getEnrolledCount(), Integer::sum);
        }

        // Print all exercise types ranked by income
        System.out.printf("\n  %-20s %-12s %-12s %-10s%n",
                "Exercise", "Price/Lesson", "Total Income", "Bookings");
        System.out.println("  " + "-".repeat(58));

        incomeMap.entrySet().stream()
                .sorted(Map.Entry.<ExerciseType, Double>comparingByValue().reversed())
                .forEach(entry -> {
                    ExerciseType type = entry.getKey();
                    System.out.printf("  %-20s £%-11.2f £%-11.2f %-10d%n",
                            type.toString(),
                            type.getPrice(),
                            entry.getValue(),
                            bookingCountMap.getOrDefault(type, 0));
                });

        // Highlight winner
        ExerciseType topExercise = incomeMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        if (topExercise != null) {
            System.out.println("\n  >>> HIGHEST INCOME: " + topExercise.toString().toUpperCase()
                    + " with £" + String.format("%.2f", incomeMap.get(topExercise)) + " total <<<");
        }
        System.out.println("\n" + "=".repeat(70));
    }
}

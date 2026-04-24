package flc.service;

import flc.model.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages the timetable of all lessons and supports timetable queries.
 * Members can search by day or by exercise name.
 */
public class TimetableService {

    private final List<Lesson> lessons = new ArrayList<>();
    private int lessonCounter = 1;

    /**
     * Creates and registers a new lesson in the timetable.
     */
    public Lesson createLesson(ExerciseType exerciseType, Day day, TimeSlot timeSlot, int weekNumber) {
        String id = "L" + String.format("%03d", lessonCounter++);
        Lesson lesson = new Lesson(id, exerciseType, day, timeSlot, weekNumber);
        lessons.add(lesson);
        return lesson;
    }

    /**
     * Returns all lessons for a given day across all weeks.
     */
    public List<Lesson> getLessonsByDay(Day day) {
        return lessons.stream()
                .filter(l -> l.getDay().equals(day))
                .collect(Collectors.toList());
    }

    /**
     * Returns all lessons for a given day in a specific week.
     */
    public List<Lesson> getLessonsByDayAndWeek(Day day, int weekNumber) {
        return lessons.stream()
                .filter(l -> l.getDay().equals(day) && l.getWeekNumber() == weekNumber)
                .collect(Collectors.toList());
    }

    /**
     * Returns all lessons for a specific exercise type across all weeks.
     */
    public List<Lesson> getLessonsByExercise(ExerciseType exerciseType) {
        return lessons.stream()
                .filter(l -> l.getExerciseType().equals(exerciseType))
                .collect(Collectors.toList());
    }

    /**
     * Returns all lessons in a specific week.
     */
    public List<Lesson> getLessonsByWeek(int weekNumber) {
        return lessons.stream()
                .filter(l -> l.getWeekNumber() == weekNumber)
                .collect(Collectors.toList());
    }

    /**
     * Finds a specific lesson by day, time slot, and week number.
     */
    public Optional<Lesson> findLesson(Day day, TimeSlot timeSlot, int weekNumber) {
        return lessons.stream()
                .filter(l -> l.getDay().equals(day)
                        && l.getTimeSlot().equals(timeSlot)
                        && l.getWeekNumber() == weekNumber)
                .findFirst();
    }

    /**
     * Finds a lesson by its ID.
     */
    public Optional<Lesson> findLessonById(String lessonId) {
        return lessons.stream()
                .filter(l -> l.getLessonId().equals(lessonId))
                .findFirst();
    }

    public List<Lesson> getAllLessons() {
        return Collections.unmodifiableList(lessons);
    }

    /**
     * Prints a formatted timetable for a given day and week to System.out.
     */
    public void printTimetableByDay(Day day, int weekNumber) {
        System.out.println("\n  === Timetable: Week " + weekNumber + " | " + day + " ===");
        System.out.printf("  %-12s %-18s %-10s %-8s%n",
                "Time Slot", "Exercise", "Price", "Spaces");
        System.out.println("  " + "-".repeat(52));
        List<Lesson> dayLessons = getLessonsByDayAndWeek(day, weekNumber);
        dayLessons.sort(Comparator.comparing(Lesson::getTimeSlot));
        for (Lesson l : dayLessons) {
            int spaces = Lesson.MAX_CAPACITY - l.getEnrolledCount();
            System.out.printf("  %-12s %-18s £%-9.2f %-8d%n",
                    l.getTimeSlot().getDisplayName(),
                    l.getExerciseType().toString(),
                    l.getPrice(),
                    spaces);
        }
    }

    /**
     * Prints a formatted timetable for a given exercise type across all weeks.
     */
    public void printTimetableByExercise(ExerciseType exerciseType) {
        System.out.println("\n  === Timetable: All '" + exerciseType + "' Lessons ===");
        System.out.printf("  %-6s %-10s %-12s %-10s %-8s%n",
                "Week", "Day", "Time Slot", "Price", "Spaces");
        System.out.println("  " + "-".repeat(52));
        List<Lesson> typeLessons = getLessonsByExercise(exerciseType);
        typeLessons.sort(Comparator.comparingInt(Lesson::getWeekNumber)
                .thenComparing(Lesson::getDay)
                .thenComparing(Lesson::getTimeSlot));
        for (Lesson l : typeLessons) {
            int spaces = Lesson.MAX_CAPACITY - l.getEnrolledCount();
            System.out.printf("  %-6d %-10s %-12s £%-9.2f %-8d%n",
                    l.getWeekNumber(),
                    l.getDay().toString(),
                    l.getTimeSlot().getDisplayName(),
                    l.getPrice(),
                    spaces);
        }
    }
}

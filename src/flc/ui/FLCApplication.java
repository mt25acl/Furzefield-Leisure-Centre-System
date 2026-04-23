package flc.ui;

import flc.data.DataLoader;
import flc.model.*;
import flc.report.ReportService;
import flc.service.*;

import java.util.*;

/**
 * Console-based UI for the Furzefield Leisure Centre booking system.
 * Entry point for the application.
 */
public class FLCApplication {

    private static MemberService    memberService;
    private static TimetableService timetableService;
    private static BookingService   bookingService;
    private static ReviewService    reviewService;
    private static ReportService    reportService;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialise services
        memberService    = new MemberService();
        timetableService = new TimetableService();
        bookingService   = new BookingService();
        reviewService    = new ReviewService();
        reportService    = new ReportService(timetableService);

        // Load seed data
        DataLoader.load(memberService, timetableService, bookingService, reviewService);

        System.out.println("================================================");
        System.out.println("  Welcome to Furzefield Leisure Centre (FLC)");
        System.out.println("================================================");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt();
            switch (choice) {
                case 1: viewTimetableByDay();      break;
                case 2: viewTimetableByExercise(); break;
                case 3: bookALesson();             break;
                case 4: changeBooking();           break;
                case 5: cancelBooking();           break;
                case 6: leaveReview();             break;
                case 7: viewMemberBookings();      break;
                case 8: reportService.printMembersPerLessonReport(); break;
                case 9: reportService.printHighestIncomeReport();    break;
                case 0: running = false; System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    // ── MENU ─────────────────────────────────────────────────────────────────

    private static void printMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. View timetable by day");
        System.out.println("2. View timetable by exercise");
        System.out.println("3. Book a lesson");
        System.out.println("4. Change a booking");
        System.out.println("5. Cancel a booking");
        System.out.println("6. Leave a review");
        System.out.println("7. View my bookings");
        System.out.println("8. Report: Members per lesson");
        System.out.println("9. Report: Highest income exercise");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    // ── TIMETABLE ────────────────────────────────────────────────────────────

    private static void viewTimetableByDay() {
        System.out.print("  Enter day (SATURDAY/SUNDAY): ");
        Day day = readDay();
        if (day == null) return;
        System.out.print("  Enter week number (1-8): ");
        int week = readInt();
        timetableService.printTimetableByDay(day, week);
    }

    private static void viewTimetableByExercise() {
        ExerciseType type = selectExerciseType();
        if (type == null) return;
        timetableService.printTimetableByExercise(type);
    }

    // ── BOOKING ──────────────────────────────────────────────────────────────

    private static void bookALesson() {
        Member member = selectMember();
        if (member == null) return;

        System.out.print("  Enter day (SATURDAY/SUNDAY): ");
        Day day = readDay();
        if (day == null) return;

        System.out.print("  Enter week number (1-8): ");
        int week = readInt();

        timetableService.printTimetableByDay(day, week);

        System.out.print("  Enter time slot (MORNING/AFTERNOON/EVENING): ");
        TimeSlot slot = readTimeSlot();
        if (slot == null) return;

        Optional<Lesson> lessonOpt = timetableService.findLesson(day, slot, week);
        if (!lessonOpt.isPresent()) {
            System.out.println("  Lesson not found.");
            return;
        }
        bookingService.bookLesson(member, lessonOpt.get());
    }

    private static void changeBooking() {
        Member member = selectMember();
        if (member == null) return;

        List<Booking> memberBookings = bookingService.getBookingsForMember(member);
        if (memberBookings.isEmpty()) {
            System.out.println("  No bookings found for " + member.getName());
            return;
        }

        System.out.println("  Current bookings:");
        for (int i = 0; i < memberBookings.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + memberBookings.get(i));
        }
        System.out.print("  Select booking number to change: ");
        int idx = readInt() - 1;
        if (idx < 0 || idx >= memberBookings.size()) {
            System.out.println("  Invalid selection.");
            return;
        }
        Booking existing = memberBookings.get(idx);

        System.out.print("  New day (SATURDAY/SUNDAY): ");
        Day day = readDay();
        if (day == null) return;

        System.out.print("  New week (1-8): ");
        int week = readInt();

        System.out.print("  New time slot (MORNING/AFTERNOON/EVENING): ");
        TimeSlot slot = readTimeSlot();
        if (slot == null) return;

        Optional<Lesson> newLessonOpt = timetableService.findLesson(day, slot, week);
        if (!newLessonOpt.isPresent()) {
            System.out.println("  Lesson not found.");
            return;
        }
        bookingService.changeBooking(existing, newLessonOpt.get());
    }

    private static void cancelBooking() {
        Member member = selectMember();
        if (member == null) return;

        List<Booking> memberBookings = bookingService.getBookingsForMember(member);
        if (memberBookings.isEmpty()) {
            System.out.println("  No bookings found.");
            return;
        }
        for (int i = 0; i < memberBookings.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + memberBookings.get(i));
        }
        System.out.print("  Select booking number to cancel: ");
        int idx = readInt() - 1;
        if (idx < 0 || idx >= memberBookings.size()) {
            System.out.println("  Invalid selection.");
            return;
        }
        bookingService.cancelBooking(memberBookings.get(idx));
    }

    // ── REVIEW ───────────────────────────────────────────────────────────────

    private static void leaveReview() {
        Member member = selectMember();
        if (member == null) return;

        List<Booking> memberBookings = bookingService.getBookingsForMember(member);
        if (memberBookings.isEmpty()) {
            System.out.println("  No lessons attended.");
            return;
        }
        System.out.println("  Your lessons:");
        for (int i = 0; i < memberBookings.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + memberBookings.get(i).getLesson());
        }
        System.out.print("  Select lesson to review: ");
        int idx = readInt() - 1;
        if (idx < 0 || idx >= memberBookings.size()) {
            System.out.println("  Invalid selection.");
            return;
        }
        Lesson lesson = memberBookings.get(idx).getLesson();

        System.out.print("  Rating (1=Very Dissatisfied, 5=Very Satisfied): ");
        int rating = readInt();

        System.out.print("  Comment: ");
        String comment = scanner.nextLine().trim();

        reviewService.addReview(member, lesson, rating, comment);
    }

    // ── MEMBER BOOKINGS ──────────────────────────────────────────────────────

    private static void viewMemberBookings() {
        Member member = selectMember();
        if (member == null) return;

        List<Booking> memberBookings = bookingService.getBookingsForMember(member);
        System.out.println("\n  Bookings for " + member.getName() + ":");
        if (memberBookings.isEmpty()) {
            System.out.println("  None.");
        } else {
            memberBookings.forEach(b -> System.out.println("  " + b));
        }
    }

    // ── HELPERS ──────────────────────────────────────────────────────────────

    private static Member selectMember() {
        Collection<Member> all = memberService.getAllMembers();
        System.out.println("  Members:");
        List<Member> list = new ArrayList<>(all);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + list.get(i).getName()
                    + " [" + list.get(i).getMemberId() + "]");
        }
        System.out.print("  Select member number: ");
        int idx = readInt() - 1;
        if (idx < 0 || idx >= list.size()) {
            System.out.println("  Invalid selection.");
            return null;
        }
        return list.get(idx);
    }

    private static ExerciseType selectExerciseType() {
        ExerciseType[] types = ExerciseType.values();
        System.out.println("  Exercise types:");
        for (int i = 0; i < types.length; i++) {
            System.out.println("  " + (i + 1) + ". " + types[i]);
        }
        System.out.print("  Select number: ");
        int idx = readInt() - 1;
        if (idx < 0 || idx >= types.length) {
            System.out.println("  Invalid selection.");
            return null;
        }
        return types[idx];
    }

    private static Day readDay() {
        try {
            return Day.valueOf(scanner.nextLine().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("  Invalid day. Use SATURDAY or SUNDAY.");
            return null;
        }
    }

    private static TimeSlot readTimeSlot() {
        try {
            return TimeSlot.valueOf(scanner.nextLine().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("  Invalid time slot. Use MORNING, AFTERNOON, or EVENING.");
            return null;
        }
    }

    private static int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

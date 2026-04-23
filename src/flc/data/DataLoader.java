package flc.data;

import flc.model.*;
import flc.service.*;

/**
 * Seeds the FLC system with 8 weekends of timetable (48 lessons),
 * 10 registered members, bookings, and 20+ reviews.
 * This is the self-contained data required by the coursework specification.
 */
public class DataLoader {

    public static void load(MemberService memberService,
                            TimetableService timetableService,
                            BookingService bookingService,
                            ReviewService reviewService) {

        System.out.println("Loading FLC data...");

        // ── 10 MEMBERS ──────────────────────────────────────────────────────
        Member Mayooran   = memberService.registerMember("Mayooran Johnson",   "Mayooran@email.com");
        Member bob     = memberService.registerMember("Bob Smith",       "bob@email.com");
        Member carol   = memberService.registerMember("Carol White",     "carol@email.com");
        Member david   = memberService.registerMember("David Brown",     "david@email.com");
        Member emma    = memberService.registerMember("Emma Davis",      "emma@email.com");
        Member frank   = memberService.registerMember("Frank Wilson",    "frank@email.com");
        Member grace   = memberService.registerMember("Grace Taylor",    "grace@email.com");
        Member henry   = memberService.registerMember("Henry Moore",     "henry@email.com");
        Member iris    = memberService.registerMember("Iris Anderson",   "iris@email.com");
        Member james   = memberService.registerMember("James Thomas",    "james@email.com");

        System.out.println("  10 members registered.");

        // ── 8 WEEKENDS OF TIMETABLE (48 lessons) ────────────────────────────
        // Week 1
        Lesson w1_sat_morn = timetableService.createLesson(ExerciseType.YOGA,      Day.SATURDAY, TimeSlot.MORNING,   1);
        Lesson w1_sat_aftn = timetableService.createLesson(ExerciseType.ZUMBA,     Day.SATURDAY, TimeSlot.AFTERNOON, 1);
        Lesson w1_sat_even = timetableService.createLesson(ExerciseType.BOX_FIT,   Day.SATURDAY, TimeSlot.EVENING,   1);
        Lesson w1_sun_morn = timetableService.createLesson(ExerciseType.AQUACISE,  Day.SUNDAY,   TimeSlot.MORNING,   1);
        Lesson w1_sun_aftn = timetableService.createLesson(ExerciseType.BODY_BLITZ,Day.SUNDAY,   TimeSlot.AFTERNOON, 1);
        Lesson w1_sun_even = timetableService.createLesson(ExerciseType.PILATES,   Day.SUNDAY,   TimeSlot.EVENING,   1);

        // Week 2
        Lesson w2_sat_morn = timetableService.createLesson(ExerciseType.ZUMBA,     Day.SATURDAY, TimeSlot.MORNING,   2);
        Lesson w2_sat_aftn = timetableService.createLesson(ExerciseType.AQUACISE,  Day.SATURDAY, TimeSlot.AFTERNOON, 2);
        Lesson w2_sat_even = timetableService.createLesson(ExerciseType.YOGA,      Day.SATURDAY, TimeSlot.EVENING,   2);
        Lesson w2_sun_morn = timetableService.createLesson(ExerciseType.BOX_FIT,   Day.SUNDAY,   TimeSlot.MORNING,   2);
        Lesson w2_sun_aftn = timetableService.createLesson(ExerciseType.PILATES,   Day.SUNDAY,   TimeSlot.AFTERNOON, 2);
        Lesson w2_sun_even = timetableService.createLesson(ExerciseType.BODY_BLITZ,Day.SUNDAY,   TimeSlot.EVENING,   2);

        // Week 3
        Lesson w3_sat_morn = timetableService.createLesson(ExerciseType.AQUACISE,  Day.SATURDAY, TimeSlot.MORNING,   3);
        Lesson w3_sat_aftn = timetableService.createLesson(ExerciseType.BODY_BLITZ,Day.SATURDAY, TimeSlot.AFTERNOON, 3);
        Lesson w3_sat_even = timetableService.createLesson(ExerciseType.ZUMBA,     Day.SATURDAY, TimeSlot.EVENING,   3);
        Lesson w3_sun_morn = timetableService.createLesson(ExerciseType.YOGA,      Day.SUNDAY,   TimeSlot.MORNING,   3);
        Lesson w3_sun_aftn = timetableService.createLesson(ExerciseType.BOX_FIT,   Day.SUNDAY,   TimeSlot.AFTERNOON, 3);
        Lesson w3_sun_even = timetableService.createLesson(ExerciseType.PILATES,   Day.SUNDAY,   TimeSlot.EVENING,   3);

        // Week 4
        Lesson w4_sat_morn = timetableService.createLesson(ExerciseType.BOX_FIT,   Day.SATURDAY, TimeSlot.MORNING,   4);
        Lesson w4_sat_aftn = timetableService.createLesson(ExerciseType.PILATES,   Day.SATURDAY, TimeSlot.AFTERNOON, 4);
        Lesson w4_sat_even = timetableService.createLesson(ExerciseType.AQUACISE,  Day.SATURDAY, TimeSlot.EVENING,   4);
        Lesson w4_sun_morn = timetableService.createLesson(ExerciseType.ZUMBA,     Day.SUNDAY,   TimeSlot.MORNING,   4);
        Lesson w4_sun_aftn = timetableService.createLesson(ExerciseType.YOGA,      Day.SUNDAY,   TimeSlot.AFTERNOON, 4);
        Lesson w4_sun_even = timetableService.createLesson(ExerciseType.BODY_BLITZ,Day.SUNDAY,   TimeSlot.EVENING,   4);

        // Week 5
        Lesson w5_sat_morn = timetableService.createLesson(ExerciseType.PILATES,   Day.SATURDAY, TimeSlot.MORNING,   5);
        Lesson w5_sat_aftn = timetableService.createLesson(ExerciseType.YOGA,      Day.SATURDAY, TimeSlot.AFTERNOON, 5);
        Lesson w5_sat_even = timetableService.createLesson(ExerciseType.BODY_BLITZ,Day.SATURDAY, TimeSlot.EVENING,   5);
        Lesson w5_sun_morn = timetableService.createLesson(ExerciseType.ZUMBA,     Day.SUNDAY,   TimeSlot.MORNING,   5);
        Lesson w5_sun_aftn = timetableService.createLesson(ExerciseType.AQUACISE,  Day.SUNDAY,   TimeSlot.AFTERNOON, 5);
        Lesson w5_sun_even = timetableService.createLesson(ExerciseType.BOX_FIT,   Day.SUNDAY,   TimeSlot.EVENING,   5);

        // Week 6
        Lesson w6_sat_morn = timetableService.createLesson(ExerciseType.BODY_BLITZ,Day.SATURDAY, TimeSlot.MORNING,   6);
        Lesson w6_sat_aftn = timetableService.createLesson(ExerciseType.BOX_FIT,   Day.SATURDAY, TimeSlot.AFTERNOON, 6);
        Lesson w6_sat_even = timetableService.createLesson(ExerciseType.PILATES,   Day.SATURDAY, TimeSlot.EVENING,   6);
        Lesson w6_sun_morn = timetableService.createLesson(ExerciseType.AQUACISE,  Day.SUNDAY,   TimeSlot.MORNING,   6);
        Lesson w6_sun_aftn = timetableService.createLesson(ExerciseType.ZUMBA,     Day.SUNDAY,   TimeSlot.AFTERNOON, 6);
        Lesson w6_sun_even = timetableService.createLesson(ExerciseType.YOGA,      Day.SUNDAY,   TimeSlot.EVENING,   6);

        // Week 7
        Lesson w7_sat_morn = timetableService.createLesson(ExerciseType.YOGA,      Day.SATURDAY, TimeSlot.MORNING,   7);
        Lesson w7_sat_aftn = timetableService.createLesson(ExerciseType.AQUACISE,  Day.SATURDAY, TimeSlot.AFTERNOON, 7);
        Lesson w7_sat_even = timetableService.createLesson(ExerciseType.ZUMBA,     Day.SATURDAY, TimeSlot.EVENING,   7);
        Lesson w7_sun_morn = timetableService.createLesson(ExerciseType.PILATES,   Day.SUNDAY,   TimeSlot.MORNING,   7);
        Lesson w7_sun_aftn = timetableService.createLesson(ExerciseType.BODY_BLITZ,Day.SUNDAY,   TimeSlot.AFTERNOON, 7);
        Lesson w7_sun_even = timetableService.createLesson(ExerciseType.BOX_FIT,   Day.SUNDAY,   TimeSlot.EVENING,   7);

        // Week 8
        Lesson w8_sat_morn = timetableService.createLesson(ExerciseType.AQUACISE,  Day.SATURDAY, TimeSlot.MORNING,   8);
        Lesson w8_sat_aftn = timetableService.createLesson(ExerciseType.ZUMBA,     Day.SATURDAY, TimeSlot.AFTERNOON, 8);
        Lesson w8_sat_even = timetableService.createLesson(ExerciseType.YOGA,      Day.SATURDAY, TimeSlot.EVENING,   8);
        Lesson w8_sun_morn = timetableService.createLesson(ExerciseType.BOX_FIT,   Day.SUNDAY,   TimeSlot.MORNING,   8);
        Lesson w8_sun_aftn = timetableService.createLesson(ExerciseType.PILATES,   Day.SUNDAY,   TimeSlot.AFTERNOON, 8);
        Lesson w8_sun_even = timetableService.createLesson(ExerciseType.BODY_BLITZ,Day.SUNDAY,   TimeSlot.EVENING,   8);

        System.out.println("  48 lessons created across 8 weekends.");

        // ── BOOKINGS ─────────────────────────────────────────────────────────
        // Week 1
        bookingService.bookLesson(Mayooran,  w1_sat_morn);
        bookingService.bookLesson(bob,    w1_sat_morn);
        bookingService.bookLesson(carol,  w1_sat_aftn);
        bookingService.bookLesson(david,  w1_sat_aftn);
        bookingService.bookLesson(emma,   w1_sat_even);
        bookingService.bookLesson(Mayooran,  w1_sun_morn);
        bookingService.bookLesson(frank,  w1_sun_morn);
        bookingService.bookLesson(grace,  w1_sun_aftn);
        bookingService.bookLesson(henry,  w1_sun_aftn);
        bookingService.bookLesson(iris,   w1_sun_even);

        // Week 2
        bookingService.bookLesson(bob,    w2_sat_morn);
        bookingService.bookLesson(carol,  w2_sat_morn);
        bookingService.bookLesson(james,  w2_sat_aftn);
        bookingService.bookLesson(Mayooran,  w2_sat_aftn);
        bookingService.bookLesson(david,  w2_sat_even);
        bookingService.bookLesson(emma,   w2_sun_morn);
        bookingService.bookLesson(frank,  w2_sun_morn);
        bookingService.bookLesson(grace,  w2_sun_aftn);

        // Week 3
        bookingService.bookLesson(henry,  w3_sat_morn);
        bookingService.bookLesson(iris,   w3_sat_morn);
        bookingService.bookLesson(james,  w3_sat_morn);
        bookingService.bookLesson(Mayooran,  w3_sat_aftn);
        bookingService.bookLesson(bob,    w3_sat_aftn);
        bookingService.bookLesson(carol,  w3_sun_morn);
        bookingService.bookLesson(david,  w3_sun_morn);
        bookingService.bookLesson(emma,   w3_sun_aftn);

        // Week 4
        bookingService.bookLesson(frank,  w4_sat_morn);
        bookingService.bookLesson(grace,  w4_sat_morn);
        bookingService.bookLesson(henry,  w4_sat_aftn);
        bookingService.bookLesson(iris,   w4_sat_aftn);
        bookingService.bookLesson(james,  w4_sun_morn);
        bookingService.bookLesson(Mayooran,  w4_sun_morn);
        bookingService.bookLesson(bob,    w4_sun_aftn);
        bookingService.bookLesson(carol,  w4_sun_even);

        System.out.println("  Bookings created.");

        // ── 20+ REVIEWS ──────────────────────────────────────────────────────
        reviewService.addReview(Mayooran, w1_sat_morn, 5, "Wonderful yoga session, very relaxing!");
        reviewService.addReview(bob,   w1_sat_morn, 4, "Great instructor, good pace.");
        reviewService.addReview(carol, w1_sat_aftn, 5, "Zumba was so much fun, I loved it!");
        reviewService.addReview(david, w1_sat_aftn, 3, "OK class but music was too loud.");
        reviewService.addReview(emma,  w1_sat_even, 4, "Box fit was intense but rewarding.");
        reviewService.addReview(Mayooran, w1_sun_morn, 5, "Aquacise is perfect for a Sunday morning.");
        reviewService.addReview(frank, w1_sun_morn, 4, "Really enjoyed the pool session.");
        reviewService.addReview(grace, w1_sun_aftn, 2, "Body Blitz was too hard for beginners.");
        reviewService.addReview(henry, w1_sun_aftn, 3, "Decent class but needs better structure.");
        reviewService.addReview(iris,  w1_sun_even, 5, "Pilates was exactly what I needed!");
        reviewService.addReview(bob,   w2_sat_morn, 4, "Good energy in the Zumba class.");
        reviewService.addReview(carol, w2_sat_morn, 5, "Best Zumba session yet!");
        reviewService.addReview(james, w2_sat_aftn, 3, "Aquacise was OK, pool a bit cold.");
        reviewService.addReview(Mayooran, w2_sat_aftn, 4, "Enjoyed the water workout.");
        reviewService.addReview(david, w2_sat_even, 5, "Evening yoga was so peaceful.");
        reviewService.addReview(emma,  w2_sun_morn, 4, "Box fit was very well-run.");
        reviewService.addReview(frank, w2_sun_morn, 5, "Loved the intensity of Box Fit!");
        reviewService.addReview(grace, w2_sun_aftn, 4, "Pilates was very calming.");
        reviewService.addReview(henry, w3_sat_morn, 3, "Aquacise was fine, nothing special.");
        reviewService.addReview(iris,  w3_sat_morn, 5, "Amazing session, will come back!");
        reviewService.addReview(james, w3_sat_morn, 4, "Great instructor in the pool.");
        reviewService.addReview(Mayooran, w3_sat_aftn, 5, "Body Blitz really pushed me!");
        reviewService.addReview(carol, w3_sun_morn, 4, "Yoga by Sunday morning is lovely.");
        reviewService.addReview(emma,  w3_sun_aftn, 4, "Box fit keeps getting better.");

        System.out.println("  24 reviews added.");
        System.out.println("Data loading complete.\n");
    }
}

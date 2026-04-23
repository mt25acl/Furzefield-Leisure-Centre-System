private final List<Exercise> exercises = new ArrayList<>();
    private final List<Lesson>   lessons   = new ArrayList<>();

    // ── Exercises ─────────────────────────────────────────────────────────────
    public void         addExercise(Exercise e)   { exercises.add(e); }
    public List<Exercise> getAllExercises()        { return Collections.unmodifiableList(exercises); }
    public Exercise getExerciseByName(String name) {
        return exercises.stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    // ── Lessons ───────────────────────────────────────────────────────────────
    public void       addLesson(Lesson l)         { lessons.add(l); }
    public List<Lesson> getAllLessons()            { return Collections.unmodifiableList(lessons); }

    public Lesson findLesson(int weekendNo, String day, TimeSlot slot) {
        return lessons.stream()
                .filter(l -> l.getWeekendNumber() == weekendNo
                          && l.getDay().equalsIgnoreCase(day)
                          && l.getTimeSlot() == slot)
                .findFirst().orElse(null);
    }

    public List<Lesson> getLessonsByDay(String day) {
        return lessons.stream()
                .filter(l -> l.getDay().equalsIgnoreCase(day))
                .collect(Collectors.toList());
    }

    public List<Lesson> getLessonsByExercise(String exerciseName) {
        return lessons.stream()
                .filter(l -> l.getExercise().getName().equalsIgnoreCase(exerciseName))
                .collect(Collectors.toList());
    }

    public List<Lesson> getLessonsByWeekend(int weekendNo) {
        return lessons.stream()
                .filter(l -> l.getWeekendNumber() == weekendNo)
                .collect(Collectors.toList());
    }
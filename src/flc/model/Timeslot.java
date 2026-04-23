package flc.model;

/**
 * Enum representing the three daily time slots for lessons.
 */
public enum TimeSlot {
    MORNING("Morning (09:00)"),
    AFTERNOON("Afternoon (14:00)"),
    EVENING("Evening (18:30)");

    private final String displayName;

    TimeSlot(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

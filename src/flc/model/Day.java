package flc.model;

/**
 * Enum representing the two weekend days.
 */
public enum Day {
    SATURDAY,
    SUNDAY;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}

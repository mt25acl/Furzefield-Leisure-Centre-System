package flc.model;

/**
 * Enum representing all available exercise types with their fixed prices.
 */
public enum ExerciseType {
    YOGA(12.00),
    ZUMBA(10.00),
    AQUACISE(15.00),
    BOX_FIT(14.00),
    BODY_BLITZ(11.00),
    PILATES(13.00);

    private final double price;

    ExerciseType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase().replace("_", " ");
    }
}


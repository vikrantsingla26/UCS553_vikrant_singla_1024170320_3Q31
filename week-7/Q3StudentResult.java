import java.util.Objects;
import java.util.Optional;

class Result<T extends Number> {
    private final T[] marks;
    private final double maximumPerSubject;

    Result(T[] marks, double maximumPerSubject) {
        this.marks = Objects.requireNonNull(marks, "Marks are required");
        if (marks.length == 0) {
            throw new IllegalArgumentException("At least one mark is required");
        }
        if (maximumPerSubject <= 0) {
            throw new IllegalArgumentException("Maximum marks must be positive");
        }
        this.maximumPerSubject = maximumPerSubject;
    }

    double average() {
        double total = 0.0;
        for (T mark : marks) {
            total += Objects.requireNonNull(mark, "A mark cannot be null").doubleValue();
        }
        return total / marks.length;
    }

    double percentage() {
        return average() / maximumPerSubject * 100.0;
    }

    boolean hasPassed(double threshold) {
        return percentage() >= threshold;
    }

    Optional<String> displayResult(double threshold) {
        return Optional.ofNullable(marks)
                .filter(values -> values.length > 0)
                .map(values -> String.format("Average: %.2f, Percentage: %.2f%%, Passed: %s",
                        average(), percentage(), hasPassed(threshold)));
    }
}

public class Q3StudentResult {
    public static void main(String[] args) {
        Double[] marks = {78.5, 84.0, 69.5};
        Result<Double> result = new Result<>(marks, 100.0);
        result.displayResult(40.0).ifPresent(System.out::println);
    }
}

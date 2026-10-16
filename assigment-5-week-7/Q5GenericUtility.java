import java.util.Objects;
import java.util.Optional;

class GenericUtility {
    public static <T> boolean isEqual(T a, T b) {
        return Objects.equals(a, b);
    }

    public static <T> T getDefault(Optional<T> value, T defaultValue) {
        return Objects.requireNonNull(value, "Optional value is required").orElse(defaultValue);
    }

    public static <T extends Comparable<? super T>> T max(T a, T b) {
        Objects.requireNonNull(a, "First value is required");
        Objects.requireNonNull(b, "Second value is required");
        return a.compareTo(b) >= 0 ? a : b;
    }

    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }
}

public class Q5GenericUtility {
    public static void main(String[] args) {
        System.out.println("Strings equal: " + GenericUtility.isEqual("Java", "Java"));
        System.out.println("Integers equal: " + GenericUtility.isEqual(12, 15));
        System.out.println("Optional default: " + GenericUtility.getDefault(Optional.<String>empty(), "N/A"));
        System.out.println("Larger integer: " + GenericUtility.max(24, 18));
        System.out.println("Larger string: " + GenericUtility.max("Pear", "Apple"));
        System.out.println("Is null: " + GenericUtility.isNull(null));
    }
}

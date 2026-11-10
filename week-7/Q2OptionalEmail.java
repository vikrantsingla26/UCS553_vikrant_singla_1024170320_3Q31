import java.util.Optional;

public class Q2OptionalEmail {
    public static void main(String[] args) {
        Optional<String> email = Optional.of("student@example.com");
        Optional<String> missingEmail = Optional.empty();

        email.ifPresent(value -> System.out.println("Email: " + value));
        System.out.println("Email or default: " + missingEmail.orElse("default@example.com"));

        try {
            String requiredEmail = missingEmail.orElseThrow(
                    () -> new IllegalStateException("Email address is missing"));
            System.out.println(requiredEmail);
        } catch (IllegalStateException exception) {
            System.out.println(exception.getMessage());
        }
    }
}

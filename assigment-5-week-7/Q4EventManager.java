import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Scanner;

public class Q4EventManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();
        System.out.print("Enter event date (yyyy-MM-dd): ");
        LocalDate eventDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter start time (HH:mm): ");
        LocalTime startTime = LocalTime.parse(scanner.nextLine());
        System.out.print("Enter venue (press Enter if unavailable): ");
        String venueInput = scanner.nextLine();
        Optional<String> venue = venueInput.isBlank() ? Optional.empty() : Optional.of(venueInput);

        LocalDate today = LocalDate.now();
        String dateStatus;
        if (eventDate.isAfter(today)) {
            dateStatus = "in the future";
        } else if (eventDate.isEqual(today)) {
            dateStatus = "today";
        } else {
            dateStatus = "in the past";
        }

        long daysRemaining = ChronoUnit.DAYS.between(today, eventDate);
        LocalTime endTime = startTime.plusHours(2);

        System.out.println("Event: " + eventName);
        System.out.println("Date: " + eventDate);
        System.out.println("Starts: " + startTime);
        System.out.println("Ends: " + endTime);
        System.out.println("Date status: " + dateStatus);
        System.out.println("Days remaining: " + daysRemaining);
        System.out.println("Venue: " + venue.orElse("Venue not specified"));
        scanner.close();
    }
}

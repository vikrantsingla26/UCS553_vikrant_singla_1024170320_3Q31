class InvalidAgeException extends Exception {

    InvalidAgeException(String message) {
        super(message);
    }
}

public class Second {

    static void checkAge(int age) throws InvalidAgeException {

        if (age < 18) {
            throw new InvalidAgeException("You must be 18 or older.");
        }

        System.out.println("You are eligible to vote.");
    }

    public static void main(String[] args) {

        int age = 15;

        try {
            checkAge(age);
        }
        catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        finally {
            System.out.println("Program execution completed.");
        }
    }
}

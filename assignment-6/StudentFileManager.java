import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** Demonstrates character-stream student records and a byte-stream file copy. */
public class StudentFileManager {
    private static final String STUDENT_FILE = "students.txt";
    private static final String BINARY_SOURCE = "source.bin";
    private static final String BINARY_COPY = "copy.bin";

    public static void writeStudentDetails(String fileName, String[] students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String student : students) {
                writer.write(student);
                writer.newLine();
            }
            System.out.println("Student records saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Could not write student records: " + e.getMessage());
        }
    }

    public static void readStudentDetails(String fileName) {
        System.out.println("Student records:");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Could not read student records: " + e.getMessage());
        }
    }

    /** Copies any file byte-for-byte, including binary files. */
    public static void copyBinaryFile(String source, String destination) {
        byte[] buffer = new byte[4096];
        try (FileInputStream input = new FileInputStream(source);
             FileOutputStream output = new FileOutputStream(destination)) {
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            System.out.println("Copied " + source + " to " + destination);
        } catch (IOException e) {
            System.err.println("Could not copy binary file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String[] students = {
            "101, Asha Patel, Computer Science",
            "102, Rohan Mehta, Information Technology",
            "103, Maya Shah, Electronics"
        };
        writeStudentDetails(STUDENT_FILE, students);
        readStudentDetails(STUDENT_FILE);

        if (args.length == 2) {
            copyBinaryFile(args[0], args[1]);
        } else {
            System.out.println("To copy a binary file: java StudentFileManager <source> <destination>");
            System.out.println("(Default demo paths: " + BINARY_SOURCE + " -> " + BINARY_COPY + ")");
        }
    }
}

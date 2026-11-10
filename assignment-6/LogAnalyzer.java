import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

/** Counts log entries and saves lines containing ERROR or WARNING. */
public class LogAnalyzer {
    public static void analyze(String inputFile, String outputFile) {
        int totalEntries = 0;
        int errorEntries = 0;
        int warningEntries = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                totalEntries++;
                String upper = line.toUpperCase(Locale.ROOT);
                boolean hasError = upper.contains("ERROR");
                boolean hasWarning = upper.contains("WARNING");
                if (hasError || hasWarning) {
                    writer.write(line);
                    writer.newLine();
                }
                if (hasError) errorEntries++;
                if (hasWarning) warningEntries++;
            }
            System.out.println("Total log entries: " + totalEntries);
            System.out.println("ERROR entries: " + errorEntries);
            System.out.println("WARNING entries: " + warningEntries);
            System.out.println("Filtered entries saved to " + outputFile);
        } catch (IOException e) {
            System.err.println("Could not analyze log file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String input = args.length > 0 ? args[0] : "application.log";
        String output = args.length > 1 ? args[1] : "filtered.log";
        analyze(input, output);
    }
}

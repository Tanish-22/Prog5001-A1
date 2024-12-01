import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AddSubjectMarks {
    public static void main(String[] args) {
        // Path to the CSV file
        String filePath = "prog5001_students_grade_2022.csv"; 

        try {
            // Create a Scanner to read the file
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // Read the header line (and ignore it)
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip the header
            }

            // Process each line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(","); // Split the line by commas

                // Skip empty lines to avoid parsing issues
                if (line.trim().isEmpty()) {
                    continue;  // Ignore blank lines
                }

                // Extract fields assuming they will always be present
                String lastName = values.length > 0 ? values[0] : "";
                String firstName = values.length > 1 ? values[1] : "";
                String studentID = values.length > 2 ? values[2] : "";

                // Parse and sum subject marks, treating missing or invalid values as 0
                double subject1 = 0.0, subject2 = 0.0, subject3 = 0.0;

                try {
                    subject1 = values.length > 3 && !values[3].isEmpty() ? Double.parseDouble(values[3]) : 0.0;
                } catch (NumberFormatException e) {
                    subject1 = 0.0;
                }
                try {
                    subject2 = values.length > 4 && !values[4].isEmpty() ? Double.parseDouble(values[4]) : 0.0;
                } catch (NumberFormatException e) {
                    subject2 = 0.0;
                }
                try {
                    subject3 = values.length > 5 && !values[5].isEmpty() ? Double.parseDouble(values[5]) : 0.0;
                } catch (NumberFormatException e) {
                    subject3 = 0.0;
                }

                // Calculate total marks
                double totalMarks = subject1 + subject2 + subject3;

                // Print the result, including individual subject marks and total
                System.out.printf("Student: %s %s | ID: %s | A1: %.2f | A2: %.2f | A3: %.2f | Total Marks: %.2f%n", 
                                  firstName, lastName, studentID, subject1, subject2, subject3, totalMarks);
            }

            // Close the scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
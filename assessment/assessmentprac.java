import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class assessmentprac {
    public static void main(String[] args) {

        String filePath = "prog5001_students_grade_2022.csv";
        List<Student> studentRecords = new ArrayList<>();

        // Load student data from the CSV file
        try {
            File inputFile = new File(filePath);
            Scanner fileReader = new Scanner(inputFile);

            // Skip the first line as it contains column headers
            if (fileReader.hasNextLine()) {
                fileReader.nextLine();
            }

            // Read and process each line of the CSV file
            while (fileReader.hasNextLine()) {
                String record = fileReader.nextLine();
                if (record.trim().isEmpty()) {
                    continue; // Ignore blank lines
                }

                String[] fields = record.split(",");
                String lastName = fields.length > 0 ? fields[0] : "";
                String firstName = fields.length > 1 ? fields[1] : "";
                String studentID = fields.length > 2 ? fields[2] : "";

                double assignment1 = parseDoubleOrDefault(fields, 3);
                double assignment2 = parseDoubleOrDefault(fields, 4);
                double assignment3 = parseDoubleOrDefault(fields, 5);

                double totalScore = assignment1 + assignment2 + assignment3;

                studentRecords.add(new Student(lastName, firstName, studentID, assignment1, assignment2, assignment3, totalScore));
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to locate the file. Please check the path and try again.");
            return;
        }

        // Initialize the menu for user interaction
        Scanner userInput = new Scanner(System.in);
        while (true) {
            displayMenu();
            int choice = userInput.nextInt();

            switch (choice) {
                case 1:
                    displayStudentRecords(studentRecords);
                    break;
                case 2:
                    filterRecordsByThreshold(studentRecords, userInput);
                    break;
                case 3:
                    displayTopAndBottomStudents(studentRecords);
                    break;
                case 4:
                    System.out.println("Thank you for using the Student Grade System. Goodbye!");
                    userInput.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option from the menu.");
            }
        }
    }

    // Display the menu options
    private static void displayMenu() {
        System.out.println("\n--- Student Grade System ---");
        System.out.println("1. View all student records");
        System.out.println("2. Filter students below a specific total mark threshold");
        System.out.println("3. View top 5 and bottom 5 students");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    // Display all student records
    private static void displayStudentRecords(List<Student> studentRecords) {
        System.out.println("\n--- All Student Records ---");
        for (Student student : studentRecords) {
            System.out.printf("Student: %s %s | ID: %s | A1: %.2f | A2: %.2f | A3: %.2f | Total: %.2f%n",
                    student.firstName, student.lastName, student.studentID, student.assignment1, student.assignment2, student.assignment3, student.totalMarks);
        }
    }

    // Filter students based on a total marks threshold
    private static void filterRecordsByThreshold(List<Student> studentRecords, Scanner userInput) {
        System.out.print("\nEnter the total marks threshold: ");
        double threshold = userInput.nextDouble();

        System.out.println("\n--- Students Below Threshold ---");
        for (Student student : studentRecords) {
            if (student.totalMarks < threshold) {
                System.out.printf("Student: %s %s | ID: %s | Total: %.2f%n",
                        student.firstName, student.lastName, student.studentID, student.totalMarks);
            }
        }
    }

    // Display the top 5 and bottom 5 students by total marks
    private static void displayTopAndBottomStudents(List<Student> studentRecords) {
        studentRecords.sort(Comparator.comparingDouble(Student::getTotalMarks).reversed());

        System.out.println("\n--- Top 5 Students ---");
        for (int i = 0; i < Math.min(5, studentRecords.size()); i++) {
            Student student = studentRecords.get(i);
            System.out.printf("Student: %s %s | ID: %s | Total: %.2f%n",
                    student.firstName, student.lastName, student.studentID, student.totalMarks);
        }

        studentRecords.sort(Comparator.comparingDouble(Student::getTotalMarks));

        System.out.println("\n--- Bottom 5 Students ---");
        for (int i = 0; i < Math.min(5, studentRecords.size()); i++) {
            Student student = studentRecords.get(i);
            System.out.printf("Student: %s %s | ID: %s | Total: %.2f%n",
                    student.firstName, student.lastName, student.studentID, student.totalMarks);
        }
    }

    // Utility to parse double values or return 0
    private static double parseDoubleOrDefault(String[] fields, int index) {
        try {
            return fields.length > index && !fields[index].isEmpty() ? Double.parseDouble(fields[index]) : 0.0;
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    // Student class for storing individual records
    static class Student {
        String lastName;
        String firstName;
        String studentID;
        double assignment1;
        double assignment2;
        double assignment3;
        double totalMarks;

        public Student(String lastName, String firstName, String studentID, double assignment1, double assignment2, double assignment3, double totalMarks) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.studentID = studentID;
            this.assignment1 = assignment1;
            this.assignment2 = assignment2;
            this.assignment3 = assignment3;
            this.totalMarks = totalMarks;
        }

        public double getTotalMarks() {
            return totalMarks;
        }
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class FilterStudents {
    public static void main(String[] args) {
        String filePath = "prog5001_students_grade_2022.csv";

        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);

            List<Student> students = new ArrayList<>();

            // Read and skip the header
            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }

            // Read data
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] values = line.split(",");
                if (line.trim().isEmpty()) continue;

                String lastName = values.length > 0 ? values[0] : "";
                String firstName = values.length > 1 ? values[1] : "";
                String studentID = values.length > 2 ? values[2] : "";

                double subject1 = parseOrZero(values, 3);
                double subject2 = parseOrZero(values, 4);
                double subject3 = parseOrZero(values, 5);

                double totalMarks = subject1 + subject2 + subject3;

                students.add(new Student(lastName, firstName, studentID, subject1, subject2, subject3, totalMarks));
            }
            fileScanner.close();

            // Get threshold from user
            Scanner inputScanner = new Scanner(System.in);
            System.out.print("Enter threshold for total marks: ");
            double threshold = inputScanner.nextDouble();

            // Filter students
            System.out.println("Students with total marks less than " + threshold + ":");
            for (Student student : students) {
                if (student.totalMarks < threshold) {
                    System.out.printf("Student: %s %s | ID: %s | Total Marks: %.2f%n", 
                                      student.firstName, student.lastName, student.studentID, student.totalMarks);
                }
            }

            inputScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    private static double parseOrZero(String[] values, int index) {
        try {
            return values.length > index && !values[index].isEmpty() ? Double.parseDouble(values[index]) : 0.0;
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    static class Student {
        String lastName;
        String firstName;
        String studentID;
        double subject1;
        double subject2;
        double subject3;
        double totalMarks;

        public Student(String lastName, String firstName, String studentID, double subject1, double subject2, double subject3, double totalMarks) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.studentID = studentID;
            this.subject1 = subject1;
            this.subject2 = subject2;
            this.subject3 = subject3;
            this.totalMarks = totalMarks;
        }
    }
}

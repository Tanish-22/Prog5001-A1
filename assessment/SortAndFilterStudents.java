import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Comparator;


public class SortAndFilterStudents {
    public static void main(String[] args) {
        String filePath = "prog5001_students_grade_2022.csv";

        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);

            List<FilterStudents.Student> students = new ArrayList<>();

            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }

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

                students.add(new FilterStudents.Student(lastName, firstName, studentID, subject1, subject2, subject3, totalMarks));
            }
            fileScanner.close();

            // Sort by total marks (descending) for Top 5
            students.sort(Comparator.comparingDouble((FilterStudents.Student s) -> s.totalMarks).reversed());
            System.out.println("Top 5 students:");
            for (int i = 0; i < Math.min(5, students.size()); i++) {
                FilterStudents.Student student = students.get(i);
                System.out.printf("Student: %s %s | ID: %s | Total Marks: %.2f%n", 
                                  student.firstName, student.lastName, student.studentID, student.totalMarks);
            }

            // Sort by total marks (ascending) for Bottom 5
            students.sort(Comparator.comparingDouble(s -> s.totalMarks));
            System.out.println("\nBottom 5 students:");
            for (int i = 0; i < Math.min(5, students.size()); i++) {
                FilterStudents.Student student = students.get(i);
                System.out.printf("Student: %s %s | ID: %s | Total Marks: %.2f%n", 
                                  student.firstName, student.lastName, student.studentID, student.totalMarks);
            }

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
}


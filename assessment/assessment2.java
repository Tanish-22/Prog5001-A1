import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class assessment2 {
    public static void main(String[] args) {
        String filePath = "prog5001_students_grade_2022.csv";
        List<Student> students = new ArrayList<>();
        
        // reading file and skipping the header

        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);

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

                students.add(new Student(lastName, firstName, studentID, subject1, subject2, subject3, totalMarks));
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            return;
        }

        // task 4
        // creatng a menu for all the activities
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nStudent Grade System Menu:");
            System.out.println("1. Display all students with their marks");
            System.out.println("2. Filter and display students with total marks below a threshold");
            System.out.println("3. Display the top 5 and bottom 5 students by total marks");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = inputScanner.nextInt();

            switch (choice) {
                case 1:
                    displayAllStudents(students);
                    break;
                case 2:
                    filterStudentsBelowThreshold(students, inputScanner);
                    break;
                case 3:
                    displayTopAndBottomStudents(students);
                    break;
                case 4:
                    System.out.println("Thankyou");
                    inputScanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Task 1 
    // display all students with their marks
    private static void displayAllStudents(List<Student> students) {
        System.out.println("\nAll Students:");
        for (Student student : students) {
            System.out.printf("Student: %s %s | ID: %s | A1: %.2f | A2: %.2f | A3: %.2f | Total Marks: %.2f%n",
                              student.firstName, student.lastName, student.studentID, student.subject1, student.subject2, student.subject3, student.totalMarks);
        }
    }
    // Task 2
    // Filter students with total marks below a threshold which is set by the user
    private static void filterStudentsBelowThreshold(List<Student> students, Scanner inputScanner) {
        System.out.print("\nEnter threshold for total marks: ");
        double threshold = inputScanner.nextDouble();

        System.out.println("Students with total marks less than " + threshold + ":");
        for (Student student : students) {
            if (student.totalMarks < threshold) {
                System.out.printf("Student: %s %s | ID: %s | Total Marks: %.2f%n",
                                  student.firstName, student.lastName, student.studentID, student.totalMarks);
            }
        }
    }
    // Task 3
    // Display the top 5 and bottom 5 students by total marks
    private static void displayTopAndBottomStudents(List<Student> students) {

        students.sort(Comparator.comparingDouble((Student s) -> s.totalMarks).reversed());

        System.out.println("\nTop 5 Students:");
        for (int i = 0; i < Math.min(5, students.size()); i++) {
            Student student = students.get(i);
            System.out.printf("Student: %s %s | ID: %s | Total Marks: %.2f%n",
                              student.firstName, student.lastName, student.studentID, student.totalMarks);
        }

        students.sort(Comparator.comparingDouble(s -> s.totalMarks));

        System.out.println("\nBottom 5 Students:");
        for (int i = 0; i < Math.min(5, students.size()); i++) {
            Student student = students.get(i);
            System.out.printf("Student: %s %s | ID: %s | Total Marks: %.2f%n",
                              student.firstName, student.lastName, student.studentID, student.totalMarks);
        }
    }

    private static double parseOrZero(String[] values, int index) {
        try {
            return values.length > index && !values[index].isEmpty() ? Double.parseDouble(values[index]) : 0.0;
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    // Student class
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

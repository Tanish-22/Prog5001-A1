import java.util.Scanner;
public class assessment1t
{
    public static  void main(String[] args){
    
       int numberofstudent = 30;
       // Defines the number of students
       
       float[] marks = new float[numberofstudent];
       // Creates an array to store marks for each student
       
       Scanner scanner = new Scanner (System.in);
        System.out.println("Enter assignment: ");
        String assignment = scanner.nextLine();
        System.out.println("assignment is: " + assignment);
       System.out.println("Give marks for 30 student: ");

       for (int i = 0; i < numberofstudent; i++) {
            float value = -1;
            boolean validInput = false;

            // Loop until a valid input is entered
            while (!validInput) {
                try {
                    System.out.print("Enter marks for student " + (i + 1) + ": ");
                    value = scanner.nextFloat();

                    // Checks that the entered mark is between 0 and 30
                    if (value >= 0 && value <= 30) {
                        marks[i] = value;
                        validInput = true; 
                    } else {
                        System.out.println("Please enter a mark between 0-30.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                    scanner.next(); 
                }
            }
        } 
     
        System.out.println("entered marks");
         for (int i = 0; i<numberofstudent; i++) {
        
           System.out.println(marks[i]);
           //Prints each student's mark
        }
        
       // Calculate min and max 
        float min = marks[0];
        float max = marks[0];
        for (int i = 1; i < marks.length; i++) {
            if (marks[i] < min) {
                min = marks[i];
            }
            if (marks[i] > max) {
                max = marks[i];
            }
        }
        System.out.println("the maximum marks are: " + max + ", the minimum marks are: " + min);
         
        float total = 0;
        for (int i = 0; i<numberofstudent; i++) {
        
           total = total + marks[i];
           
        }
        //Calculate mean
        float mean = 0;
        mean = total/numberofstudent;
        
        float d =0;
        for (int i = 0; i<numberofstudent; i++) {
        
           d = d + (marks[i] -mean) * (marks[i] -mean);
           
           d =d / numberofstudent;
           
           
        }
       //Calculate standard deviation
        float varianceSum = 0;
        for (int i = 0; i<numberofstudent; i++) {
            float difference = marks[i] - mean;
            varianceSum = varianceSum + difference * difference;
        }
        double variance = varianceSum / numberofstudent;
        double stdDev = Math.sqrt(variance);
        
        System.out.println("the mean is: " + mean + ", the standard deviation is: " + stdDev);
         // Display mean and standard deviation
        scanner.close();
    }
}

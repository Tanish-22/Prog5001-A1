
/**
 * Write a description of class prac here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class prac
{
   public static  void main(String[] args){
    
       int numberofstudent = 5;
       
       float[] marks = new float[numberofstudent];
       
       Scanner scanner = new Scanner (System.in);
       
       System.out.println("Give marks for 5 student: ");
       
       for (int i = 0; i<numberofstudent; i++) {
        
           float value = scanner.nextFloat();
           
           
           if(value <0 || value >30){
               System.out.println("invalid input please enter between 0-30 again");
               continue;
               
            }
           marks[i] = value;
           
           
        } 
    
        System.out.println("entered marks");
         for (int i = 0; i<numberofstudent; i++) {
        
           System.out.println(marks[i]);
           
           
        } 
       
       
    }
}

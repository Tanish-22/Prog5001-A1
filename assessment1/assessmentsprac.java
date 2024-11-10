
/**
 * Write a description of class assessmentsprac here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;

public class assessmentsprac
{
   public static void main(String[] args) {
       System.out.println("Input the radius: ");
       double radius = (new Scanner (System.in)).nextDouble();
       double area = radius * radius * 3.14;
       System.out.println("the area is : " +area);
    }
}

package jdbc.examples;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Assignment_Input_Dob_Output_Dayname {
    public static void main(String args[]) throws ParseException
{
   Scanner kb=new Scanner(System.in);
   System.out.println("Enter your date of birth:(dd-MMM-yyyy)");
   String birthstr=kb.next();
   SimpleDateFormat sdf1=new SimpleDateFormat("dd-MMM-yyyy"); 
   Date mybday=sdf1.parse(birthstr);
   System.out.println("You were born on :"+mybday);
   SimpleDateFormat sdf2=new SimpleDateFormat("EEEE");
   String day=sdf2.format(mybday); 
   System.out.println("You were born on :"+day);
}
}
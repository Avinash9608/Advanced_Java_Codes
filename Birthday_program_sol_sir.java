

package jdbc.examples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Birthday_program_sol_sir {
    public static void main(String [] args)
    {
        Scanner kb=new Scanner(System.in);
        System.out.println("Enter your DOB(MMM/dd/yyyy)");
        String dobstr=kb.next();
        SimpleDateFormat sdf=new SimpleDateFormat("MMM/dd/yyyy");
       try
       {
           Date dt=sdf.parse(dobstr);
           SimpleDateFormat sdf2=new SimpleDateFormat("EEEE");
           String dayname=sdf2.format(dt);
           System.out.println("Your born day is "+dayname);
       }
       catch(ParseException ex)
       {
           System.out.println("Wrong date pattern "+ex.getMessage());
       }
    }
}

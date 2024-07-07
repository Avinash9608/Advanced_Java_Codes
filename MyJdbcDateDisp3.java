/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.examples;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author user
 */
public class MyJdbcDateDisp3 {
    public static void main(String [] args)
    {
        Date today=new Date();
        //System.out.println(today); //Output:- Sat Oct 15 13:46:01 IST 2022
        SimpleDateFormat sdf=new SimpleDateFormat("MMMM-dd-yyyy");
        String str=sdf.format(today);
        System.out.println("Current date is "+str);
        
    }
}

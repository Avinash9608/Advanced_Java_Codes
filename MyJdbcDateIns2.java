/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class MyJdbcDateIns2 {
    public static void main(String [] args)
	{
		 Connection conn=null;
                try
                {
                     Class.forName("oracle.jdbc.OracleDriver");
                     System.out.println("Driver loaded successfully!");
                      conn=DriverManager.getConnection("jdbc:oracle:thin:@//AvinashKumar:1521/XE","advjavabatch5","mystudents");
                     System.out.println("Connection opened to the DB successfully!");
                     PreparedStatement ps=conn.prepareStatement("insert into students values(?,?,?)");
                     Scanner kb=new Scanner(System.in);
                     System.out.println("Enter roll no");
                     int roll=kb.nextInt();
                     System.out.println("Enter name");
                     kb.nextLine();
                     String name=kb.nextLine();
                     
                     System.out.println("Enter DOB(MMM/dd/yyy)");
                     String dobstr=kb.next();
                     SimpleDateFormat sdf=new SimpleDateFormat("MMM/dd/yyyy");
                     java.util.Date d1=sdf.parse(dobstr);
                     long ms=d1.getTime();
                     java.sql.Date d2=new java.sql.Date(ms);
                     
                     ps.setInt(1, roll);
                     ps.setString(2, name);
                     ps.setDate(3,d2);
                     
                     int res=ps.executeUpdate();
                     System.out.println("Record inserted :"+res);
                }
               catch(ClassNotFoundException cnf)
                {
                    System.out.println("Cannot load the driver class:"+cnf.getMessage());
                }
               catch(SQLException sqlex)
                {
                    System.out.println("Problem in DB:"+sqlex.getMessage());
                }
                catch(ParseException ex)
                {
                    System.out.println("Wrong date Pattern"+ex.getMessage());
                }
		finally
		{
			  try
                        {
                         if(conn!=null)
                         {
                         	conn.close();
                         	System.out.println("Connection closed successfully!");
                         }
                        }
                         catch(SQLException sqlex)
                	{
                    	     System.out.println("Problem in closing the connection:"+sqlex.getMessage());
                	}
               }
       }
}

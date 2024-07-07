/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class MyInsertableRes2 {
     public static void main(String [] args)
	{
		 Connection conn=null;
                try
                {
                     Class.forName("oracle.jdbc.OracleDriver");
                     System.out.println("Driver loaded successfully!");
                      conn=DriverManager.getConnection("jdbc:oracle:thin:@//AvinashKumar:1521/XE","advjavabatch5","mystudents");
                     System.out.println("Connection opened to the DB successfully!");
                     Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                     ResultSet rs=st.executeQuery("Select a.* from allbooks a");
                     Scanner kb=new Scanner(System.in);
                     
                     System.out.println("Enter bookid");
                     int id=kb.nextInt();
                     System.out.println("Enter bookname");
                     kb.nextLine();
                     String name=kb.nextLine();
                     System.out.println("Enter bookprice");
                     double amt=kb.nextDouble();
                     System.out.println("Enter subject ");
                     kb.nextLine();
                     String subj=kb.nextLine();
                     
                     rs.moveToInsertRow();
                     rs.updateInt(1, id);
                     rs.updateString(2, name);
                     rs.updateDouble(3,amt);
                     rs.updateString(4, subj);
                     rs.insertRow();
                     rs.moveToCurrentRow();
                     System.out.println("Record Inserted successfully");
                }
               catch(ClassNotFoundException cnf)
                {
                    System.out.println("Cannot load the driver class:"+cnf.getMessage());
                }
               catch(SQLException sqlex)
                {
                    System.out.println("Problem in DB:"+sqlex.getMessage());
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class MyJdbcImgIns1 {
     public static void main(String [] args)
	{
		 Connection conn=null;
                try
                {
                     Class.forName("oracle.jdbc.OracleDriver");
                     System.out.println("Driver loaded successfully!");
                      conn=DriverManager.getConnection("jdbc:oracle:thin:@//AvinashKumar:1521/XE","advjavabatch5","mystudents");
                     System.out.println("Connection opened to the DB successfully!");
                     PreparedStatement ps=conn.prepareStatement("insert into movies values(?,?)");
                     Scanner kb=new Scanner(System.in);
                     System.out.println("Enter complete path to the movie image");
                     String filepath=kb.nextLine();
                     File f=new File(filepath);
                     FileInputStream fin=new FileInputStream(filepath);
                     ps.setBinaryStream(2, fin,(int)f.length());
                     int pos=filepath.lastIndexOf("/");
                     String filename=filepath.substring(pos+1);
                     int dotpos=filename.lastIndexOf(".");
                     String filename2=filename.substring(0,dotpos);
                     
                     ps.setString(1,filename2); 
                     int res=ps.executeUpdate();
                     System.out.println("Record Inserted "+res);
                     
                }
               catch(ClassNotFoundException cnf)
                {
                    System.out.println("Cannot load the driver class:"+cnf.getMessage());
                }
               catch(SQLException sqlex)
                {
                    System.out.println("Problem in DB:"+sqlex.getMessage());
                }
                catch(IOException ex)
                {
                    System.out.println("Wrong path: "+ex.getMessage());
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

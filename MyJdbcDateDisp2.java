/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.examples;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 *
 * @author user
 */
public class MyJdbcDateDisp2 {
     public static void main(String [] args)
	{
		 Connection conn=null;
                try
                {
                     Class.forName("oracle.jdbc.OracleDriver");
                     System.out.println("Driver loaded successfully!");
                      conn=DriverManager.getConnection("jdbc:oracle:thin:@//AvinashKumar:1521/XE","advjavabatch5","mystudents");
                     System.out.println("Connection opened to the DB successfully!");
                     Statement st=conn.createStatement();
                     ResultSet rs=st.executeQuery("Select ename,hiredate from emp");
                     SimpleDateFormat sdf=new SimpleDateFormat("dd-MMMM-YYYY");
                     SimpleDateFormat sdf2=new SimpleDateFormat("EEEE");
                     while(rs.next())
                     {
                         String name=rs.getString(1);
                         Date hd=rs.getDate(2);
                         String dtstr=sdf.format(hd);
                         String dayname=sdf2.format(hd);
                         if(dayname.startsWith("S"))
                             name="*"+name;
                         System.out.println(name+", "+dtstr+","+dayname);
                     }
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

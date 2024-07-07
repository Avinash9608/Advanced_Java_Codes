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

/**
 *
 * @author user
 */
public class MyJdbcApp_before_first {
     public static void main(String [] args)
	{
		 Connection conn=null;
                try
                {
                     Class.forName("oracle.jdbc.OracleDriver");
                     System.out.println("Driver loaded successfully!");
                      conn=DriverManager.getConnection("jdbc:oracle:thin:@//AvinashKumar:1521/XE","advjavabatch5","mystudents");
                     System.out.println("Connection opened to the DB successfully!");
//                     Statement st=conn.createStatement();
                       Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                     ResultSet rs=st.executeQuery("Select ename,sal from emp");
                     while(rs.next())
                     {
                         String name=rs.getString(1);
                         int income=rs.getInt(2);
                         System.out.println(name+"\t"+income);
                     }
                     System.out.println("Again traversing in forward dir...");
                     rs.beforeFirst();
                     while(rs.next())
                     {
                         String name=rs.getString(1);
                         int income=rs.getInt(2);
                         System.out.println(name+"\t"+income);
                     }
//                   System.out.println(ResultSet.TYPE_FORWARD_ONLY);
//                   System.out.println(ResultSet.TYPE_SCROLL_INSENSITIVE);
//                   System.out.println(ResultSet.TYPE_SCROLL_SENSITIVE);
                    
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

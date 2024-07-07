
package jdbc.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyDeleteRes {
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
                     ResultSet rs=st.executeQuery("Select bookname from allbooks ");
                     List<String> bookList=new ArrayList<>();
                     Scanner kb=new Scanner(System.in);
                     
                     while(rs.next())
                     {
                        String bookname=rs.getNString(1);
                       System.out.println("Do you want to delete "+bookname+"(yes/no)");
                       String choice=kb.next();
                       if(choice.equalsIgnoreCase("yes"))
                       {
                           rs.deleteRow();
                          bookList.add(bookname);
                       }
                     }
                     if(bookList.size()==0)
                     {
                         System.out.println("No book deleted: ");
                     }
                     else
                     {
                         System.out.println(bookList.size()+"books deleted");
                          for(String name:bookList)
                            {
                                System.out.println(name);
                            }
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

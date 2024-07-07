
package jdbc.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MyUpdatableRes3 {
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
                     ResultSet rs=st.executeQuery("Select subject, bookprice, bookname from allbooks");
                     List<Book> bookList=new ArrayList<>();
                     while(rs.next())
                     {
                        String sub=rs.getNString(1);
                        if(sub.equalsIgnoreCase("jee"))
                        {
                            double amt=rs.getDouble(2);
                            double newamt=amt+amt*0.1;
                            rs.updateDouble(2, amt);
                            rs.updateRow();
                            Book b=new Book(rs.getString(3),amt,newamt);
                            bookList.add(b);
                        }
                     }
                    System.out.println("Total books updated "+bookList.size());
                    for(Book book:bookList)
                            {
                                System.out.println(book);
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

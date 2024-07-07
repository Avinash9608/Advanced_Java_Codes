/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.examples;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author avina
 */
public class MyJdbcDisConnApp1 {
    public static void main(String [] args)
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded successfully ");
            CachedRowSet crs=new CachedRowSetImpl();
            crs.setUrl("jdbc:oracle:thin:@//LAPTOP-5CA5E0U0:1521/XE");
            crs.setUsername("advjavabatch5");
            crs.setPassword("mystudents");
            crs.setCommand("select bookname, bookprice from allbooks");
            crs.execute();
            while(crs.next())
            {
                String name=crs.getString(1);
                double price=crs.getDouble(2);
                System.out.println(name+"\t "+price);
            }
        }
        catch(ClassNotFoundException cnf)
        {
            System.out.println("Cannot load the driver class "+cnf.getMessage());            
        }
        catch(SQLException sqlex)
        {
            System.out.println("Problem in BD "+sqlex.getMessage());
        }
    }
}

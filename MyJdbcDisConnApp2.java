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
public class MyJdbcDisConnApp2 {
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
            crs.setCommand("select subject, bookprice from allbooks");
            crs.execute();
            int count=0;
            while(crs.next())
            {
                String subject=crs.getString(1);
                if(subject.equalsIgnoreCase("python")||subject.equalsIgnoreCase("Django"))
                {
                    double amt=crs.getDouble(2);
                    amt+=100;
                    crs.updateDouble(2, amt);
                    crs.updateRow();
                    ++count;
                }
            }
            if(count!=0)
            {
                crs.acceptChanges();
                System.out.println(count+" python books updated!");
            }
            else
            {
                System.out.println("No books of python in DB");
            }
            
        }
        catch(ClassNotFoundException cnf)
        {
            System.out.println("Cannot load the driver class: "+cnf.getMessage());
        }
        catch(SQLException sqlex)
        {
            System.out.println("Problem in DB: "+sqlex.getMessage());
        }
    }
}

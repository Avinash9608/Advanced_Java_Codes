/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.examples;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author HP
 */
public class MyBatchUpdateAssignment {
    public static void main(String [] args)
    {
        Connection conn=null;
        boolean status=false;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver Loaded Successfully");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//AvinashKumar:1521/XE","advjavabatch5","mystudents");
            System.out.println("Connection opened to the DB successfully");
            Statement st=conn.createStatement();
            conn.setAutoCommit(false);
            st.addBatch("Insert into emp values(110,'Deepesh',1415,10,'15-AUG-1947')");
            st.addBatch("Update expenses set amount=amount+1415 where dptno=10");
            int []arr=st.executeBatch();
            for(int i=0;i<arr.length;i++)
            {
                int val=arr[i];
                switch(val)
                {
                    case Statement.SUCCESS_NO_INFO:
                        System.out.println("Query "+(i+1)+" effected unknown number of rows");
                        break;
                    case Statement.EXECUTE_FAILED:
                        System.out.println("Query "+(i+1)+" generate exception but driver execute the code");
                        break;
                    default:
                        System.out.println("Query "+(i+1)+" effected "+val+" rows ");
                }
            }
            status=true;
        }
        
        catch(BatchUpdateException ex)
        {
            int []arr=ex.getUpdateCounts();
            System.out.println("Query "+(arr.length+1)+" generated exception");
        }
        catch(ClassNotFoundException cnf)
        {
            System.out.println("Cannot load the driver class: "+cnf.getMessage());
        }
        catch(SQLException sqlex)
        {
            System.out.println("Problem in DB: "+sqlex.getMessage());
        }
        finally
        {
            try
            {
                if(conn!=null)
                {
                    if(status)
                    {
                        conn.commit();
                        System.out.println("Batch executed successfully!");
                    }
                    else
                    {
                        conn.rollback();
                        System.out.println("Batch execution rollback! ");
                    }
                    conn.close();
                    System.out.println("Connection closed successfully! ");
                }
            }
            catch(SQLException sqlex)
            {
                System.out.println("Problem in closing the connection: "+sqlex.getMessage());
            }
        }
    }
}

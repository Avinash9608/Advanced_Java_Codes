/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.examples;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author avina
 */
public class MyBatchUpdateApp2 {
    public static void main(String [] args)
    {
        Connection conn=null;
        boolean status=false;
        Scanner kb=new Scanner(System.in);
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver class loaded successfully ");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-5CA5E0U0:1521/XE","advjavabatch5","mystudents");
            System.out.println("Connection opened to the DB successfully ");
            PreparedStatement ps=conn.prepareStatement("insert into allbooks values(?,?,?,?)");
            conn.setAutoCommit(false);
            String choice;
            do
            {
                System.out.println("Enter bookid: ");
                int bno=kb.nextInt();
                
                System.out.println("Enter book name");
                kb.nextLine();
                String name=kb.nextLine();
                
                System.out.println("Enter book price ");
                double pr=kb.nextDouble();
                
                System.out.println("Enter subject ");
                kb.nextLine();
                String subj=kb.nextLine();
                
                ps.setInt(1, bno);
                ps.setString(2,name);
                ps.setDouble(3, pr);
                ps.setString(4, subj);
                
                ps.addBatch();
                System.out.println("Do you want to continue(yes/no)?");
                choice=kb.next();
            }while(choice.equalsIgnoreCase("yes"));
            
            int []arr=ps.executeBatch();
            for(int i=0;i<arr.length;i++)
            {
                int value=arr[i];
                switch(value)
                {
                    case Statement.SUCCESS_NO_INFO:
                        System.out.println("Query:"+(i+1)+" executed but no of records affected are unknown");
                        break;
                    case Statement.EXECUTE_FAILED:
                        System.out.println("Query:"+(i+1)+" give exception but driver dicided to continue");
                        break;
                    default:
                        System.out.println("Query: "+(i+1)+" affected "+value+" no of row ");
                }
            }
            status=true;
        }
        catch(BatchUpdateException BUE)
        {
            int []arr=BUE.getUpdateCounts();
            System.out.println("Query "+(arr.length+1)+" generated exception ");
        }
        catch(ClassNotFoundException cnf)
        {
            System.out.println("Cannot load the driver class "+cnf.getMessage());
        }
         catch(SQLException sqlex)
        {
            System.out.println("Problem in DB "+sqlex.getMessage());
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
                        System.out.println("Batch executed successfully ");
                    }
                    else
                    {
                        conn.rollback();
                        System.out.println("Batch execution rollback!");
                    }
                    conn.close();
                    System.out.println("Connection closed successfully!");
                }
            }
            catch(SQLException sqlex)
            {
                System.out.println("Problem in closing the connection: "+sqlex.getMessage());
            }
        }
    }
}

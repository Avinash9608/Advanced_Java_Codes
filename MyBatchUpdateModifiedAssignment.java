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
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class MyBatchUpdateModifiedAssignment {
    public static void main(String [] args)
    {
        boolean status=false;
        Connection conn=null;
        try
        {
           Class.forName("oracle.jdbc.OracleDriver");
           System.out.println("Driver loaded successfully");
           conn=DriverManager.getConnection("jdbc:oracle:thin:@//AvinashKumar:1521/XE","advjavabatch5","mystudents");
           System.out.println("connection open to DB successfully");
           PreparedStatement insertEmp=conn.prepareStatement("Insert into emp values(?,?,?,?,?)");
           PreparedStatement updateExpenses=conn.prepareStatement("update expenses set amount=amount+? where dptno=?");
           Scanner kb=new Scanner(System.in);
           System.out.println("Enter employee number:");
           int eno = kb.nextInt();
           System.out.println("Enter employee name:");
           kb.nextLine();
           String ename = kb.next();
           System.out.println("Enter employee salary:");
           double sal = kb.nextDouble();
           System.out.println("Enter department number:");
           int dptno = kb.nextInt();
           System.out.println("Enter hire date (format: DD-MON-YYYY):");
           kb.nextLine();
           String hireDate=kb.nextLine();
           
           insertEmp.setInt(1, eno);
           insertEmp.setString(2, ename);
           insertEmp.setDouble(3,sal);
           insertEmp.setInt(4, dptno);
           insertEmp.setString(5, hireDate);
           insertEmp.addBatch();
           
          updateExpenses.setDouble(1, sal);
          updateExpenses.setInt(2, dptno);
          updateExpenses.addBatch();
          
          int[] insertResult = insertEmp.executeBatch();
          int[] updateResult = updateExpenses.executeBatch();
           for (int i = 0; i < insertResult.length; i++) {
                System.out.println("Query " + (i + 1) + " inserted " + insertResult[i] + " rows.");
            }
            for (int i = 0; i < updateResult.length; i++) {
                System.out.println("Query " + (i + 1) + " updated " + updateResult[i] + " rows.");
            }
            
            status = true;
        } catch (BatchUpdateException ex) {
            int[] arr = ex.getUpdateCounts();
            System.out.println("Query " + (arr.length + 1) + " generated exception");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Cannot load the driver class: " + cnf.getMessage());
        } catch (SQLException sqlex) {
            System.out.println("Problem in DB: " + sqlex.getMessage());
        } finally {
            try {
                if (conn != null) {
                    if (status) {
                        conn.commit();
                        System.out.println("Batch executed successfully!");
                    } else {
                        conn.rollback();
                        System.out.println("Batch execution rollback! ");
                    }
                    conn.close();
                    System.out.println("Connection closed successfully! ");
                }
            } catch (SQLException sqlex) {
                System.out.println("Problem in closing the connection: " + sqlex.getMessage());
            }
        }
    }
}

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
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class MyBatchUpdateUsingStatementInterface {
     public static void main(String[] args) {
        Connection conn = null;
        boolean status = false;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver Loaded Successfully");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//AvinashKumar:1521/XE", "advjavabatch5", "mystudents");
            System.out.println("Connection opened to the DB successfully");
            Statement st = conn.createStatement();
            conn.setAutoCommit(false);

            Scanner kb = new Scanner(System.in);
            System.out.println("Enter employee number:");
            int eno = kb.nextInt();
            System.out.println("Enter employee name:");
            String ename = kb.next();
            System.out.println("Enter employee salary:");
            int sal = kb.nextInt();
            System.out.println("Enter department number:");
            int dptno = kb.nextInt();
            System.out.println("Enter hire date (format: 'DD-MON-YYYY'):");
            String hiredate = kb.next();
            String insertEmpQuery = "INSERT INTO emp VALUES (" + eno + ", '" + ename + "', " + sal + ", " + dptno + ", '" + hiredate + "')";
            st.addBatch(insertEmpQuery);

           
            String updateExpensesQuery = "UPDATE expenses SET amount = amount + " + sal + " WHERE dptno = " + dptno;
            st.addBatch(updateExpensesQuery);

            int[] updateCounts = st.executeBatch();
            conn.commit();
            status = true;

            for (int i = 0; i < updateCounts.length; i++) {
                if (updateCounts[i] == Statement.EXECUTE_FAILED || updateCounts[i] == Statement.SUCCESS_NO_INFO) {
                    System.out.println("Query " + (i + 1) + " generated an exception or affected an unknown number of rows");
                } else {
                    System.out.println("Query " + (i + 1) + " successfully executed and affected " + updateCounts[i] + " rows.");
                }
            }
        } catch (BatchUpdateException ex) {
            int[] arr = ex.getUpdateCounts();
            System.out.println("Query " + (arr.length + 1) + " generated an exception");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Cannot load the driver class: " + cnf.getMessage());
        } catch (SQLException sqlex) {
            System.out.println("Problem in DB: " + sqlex.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    System.out.println("Rollback failed: " + rollbackEx.getMessage());
                }
            }
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

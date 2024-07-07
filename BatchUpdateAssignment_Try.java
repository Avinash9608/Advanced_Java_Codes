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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author avina
 */
public class BatchUpdateAssignment_Try {
    public static void main(String [] args)
    {
        Connection conn=null;
        boolean status=false;
        Scanner kb=new Scanner(System.in);
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver Loaded Successfully");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-5CA5E0U0:1521/XE","advjavabatch5","mystudents");
            System.out.println("Connection opened to the DB successfully");
            PreparedStatement ps=conn.prepareStatement("insert into emp values(?,?,?,?,?)");
            conn.setAutoCommit(false);
            System.out.println("Enter emp no");
            int eno=kb.nextInt();
            
            System.out.println("Enter emp name");
            kb.nextLine();
            String name=kb.nextLine();
            
            System.out.println("Enter emp salary ");
            Double sal=kb.nextDouble();
            
            System.out.println("Enter emp deptno");
            int dptno=kb.nextInt();
            
            System.out.println("Enter Joining Date(MMM/dd/yyy)");
            String str=kb.next();
            SimpleDateFormat sdf=new SimpleDateFormat("MMM/dd/yyyy");
            java.util.Date d1=sdf.parse(str);
            long ms=d1.getTime();
            java.sql.Date d2=new java.sql.Date(ms);
            
            ps.setInt(1, eno);
            ps.setString(2, name);
            ps.setDouble(3, sal);
            ps.setInt(4,dptno);
            ps.setDate(5, d2);
            ps.addBatch();
            
            PreparedStatement psp=conn.prepareStatement("Update expenses set amount=amount+? where deptno=?");
            psp.setDouble(1,sal);
            psp.setInt(2, dptno);
            psp.addBatch();
             int []count=ps.executeBatch();
            for(int i=0;i<count.length;i++)
            {
                int val=count[i];
                System.out.println("Emp count "+count.length);
                
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
            
            int []cout=psp.executeBatch();
            System.out.println("Emp cout "+cout.length);
            for(int i=0;i<count.length;i++)
            {
                int val=cout[i];
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
        catch(BatchUpdateException bue)
        {
            int []arr=bue.getUpdateCounts();
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
        catch(ParseException ex)
                {
                    System.out.println("Wrong date Pattern"+ex.getMessage());
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

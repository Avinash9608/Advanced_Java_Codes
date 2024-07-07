/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author avina
 */
public class MyJdbcImgRet {
    public static void main(String [] args)
    {
        Connection conn=null;
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver Loaded Successfully");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//AvinashKumar:1521/XE","advjavabatch5","mystudents");
            System.out.println("Connection opened to DB successfully");
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("Select * from movies");
            File mydir=new File("d:/mydb_pics");
            boolean status=mydir.mkdir();
            if(status==true)
            {
                System.out.println("Folder Created");
            }
            while(rs.next())
            {
                String imgName=rs.getString(1)+".jpg";
                FileOutputStream fout=new FileOutputStream(mydir.getAbsolutePath()+"/"+imgName);
                Blob obj=rs.getBlob(2);
                byte []arr=obj.getBytes(1, (int)obj.length());
                fout.write(arr);
                fout.close();
                System.out.println(imgName+"saved successfully!");
            }
        }
        catch(ClassNotFoundException cnf)
        {
            System.out.println("Cannot load the driver class:"+cnf.getMessage());
        }
        catch(SQLException sqlex)
        {
            System.out.println("Problem in DB: "+sqlex.getMessage());
        }
        catch(IOException ex)
        {
            System.out.println("Wrong path: "+ex.getMessage());
        }
        finally
        {
            try
            {
                if(conn!=null)
                {
                    conn.close();
                    System.out.println("Connection closed successfully");
                }
            }
            catch(SQLException sqlex)
            {
                System.out.println("Problem in closing the connection: "+sqlex.getMessage());
            }
        }
    }
}

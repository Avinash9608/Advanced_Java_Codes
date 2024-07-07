/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.examples;

/**
 *
 * @author avina
 */
public class MyJdbcDisConnApp3 {
    public static void main(String [] args)
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            
        }
        catch()
    }
}

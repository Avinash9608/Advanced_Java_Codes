/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.examples;

/**
 *
 * @author HP
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyJdbcImageRetrive {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");

            // Establish a connection
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//AvinashKumar:1521/XE", "advjavabatch5", "mystudents");

            // SQL query to retrieve all images
            String sql = "SELECT MOV_NAME, IMG FROM movies";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String movieName = resultSet.getString("MOV_NAME");
                InputStream inputStream = resultSet.getBinaryStream("IMG");

                // Specify the path where you want to save the retrieved image
                String outputPath = "D:\\Assignments\\Test Paper\\Test paper4" + movieName + ".jpg";

                // Save the image to a file
                saveImageToFile(inputStream, outputPath);

                System.out.println("Image for " + movieName + " retrieved and saved successfully!");
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Connection closed successfully!");
                }
            } catch (SQLException sqlex) {
                System.out.println("Problem in closing the connection: " + sqlex.getMessage());
            }
        }
    }

    private static void saveImageToFile(InputStream inputStream, String outputPath) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}



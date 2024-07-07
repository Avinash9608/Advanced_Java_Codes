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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/InsertDataServlet")
public class InsertDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        // Instantiate the servlet
        InsertDataServlet servlet = new InsertDataServlet();

        // Sample test data
        String empNo = "1001";
        String empName = "John Doe";
        String empSal = "50000";
        String empDeptNo = "101";
        String empHireDate = "2024-03-30";

        // Call doPost method directly
        try {
            servlet.doPost(createMockRequest(empNo, empName, empSal, empDeptNo, empHireDate), createMockResponse());
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private static HttpServletRequest createMockRequest(final String empNo, final String empName, final String empSal,
                                                         final String empDeptNo, final String empHireDate) {
        return new HttpServletRequest() {
            @Override
            public String getParameter(String name) {
                switch (name) {
                    case "emp_no":
                        return empNo;
                    case "emp_name":
                        return empName;
                    case "emp_sal":
                        return empSal;
                    case "emp_dept_no":
                        return empDeptNo;
                    case "emp_hire_date":
                        return empHireDate;
                    default:
                        return null;
                }
            }
            // Other HttpServletRequest methods can be implemented as needed
        };
    }

    private static HttpServletResponse createMockResponse() {
        return new HttpServletResponse() {
            // HttpServletResponse methods can be implemented as needed
        };
    }

    // doPost method implementation remains the same as before
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // doPost method implementation
    }
}

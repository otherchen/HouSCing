package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="UserController", urlPatterns="/user")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UserController(){
		//constructor
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	//redirect to intended page
    	request.getRequestDispatcher("/src/user.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	Connection conn = null;
		PreparedStatement prepStmt = null;
		ResultSet result = null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/csci201_database?user=root&password=212340Cba");		
			
			/********************************************/
					  // Do all logic here! //
			
			
			
			
			
			
			/********************************************/
			
    	} catch (SQLException e) {
			request.setAttribute("success", false);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/src/user.jsp").forward(request, response);
			return;
		} catch (ClassNotFoundException e) {
			request.setAttribute("success", false);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/src/user.jsp").forward(request, response);
			return;
		} finally {
			try {
				result.close();
				prepStmt.close();
				conn.close();
			} catch (SQLException e) {
				//SQLException caught
			}
			
		}
    	
    }
}
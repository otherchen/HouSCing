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
import javax.servlet.http.HttpSession;

import model.User;

@WebServlet(name="TopHousingController", urlPatterns="/top-housing")
public class TopHousingController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public TopHousingController(){
		//constructor
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	//redirect to intended page
    	Connection conn = null;
		PreparedStatement prepStmt = null;
		ResultSet result = null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/csci201_database?user=root&password=212340Cba");		
			
			/********************************************/
					  // Do all logic here! //
			
			
			HttpSession session = request.getSession();
    		User curr = (User)session.getAttribute("curr");
    		if(curr == null){
    			request.setAttribute("success", false);
				request.setAttribute("message", "Session timed-out!");
    			request.getRequestDispatcher("/src/login.jsp").forward(request, response);
    		} else {
	    		prepStmt = conn.prepareStatement("SELECT * FROM top_housing_table LEFT JOIN housing_table ON top_housing_table.housingID = housing_table.keyid"
						+ " WHERE top_housing_table.userID = ?");
				prepStmt.setInt(1, curr.getUserID());
				result = prepStmt.executeQuery();
	    		
				//redirecting pages
				request.setAttribute("top-housing", result);
	    		request.getRequestDispatcher("/src/top-housing.jsp").forward(request, response);
	    		return;
    		}		
			
			/********************************************/
			
    	} catch (SQLException e) {
			request.setAttribute("success", false);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/src/top-housing.jsp").forward(request, response);
			return;
		} catch (ClassNotFoundException e) {
			request.setAttribute("success", false);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/src/top-housing.jsp").forward(request, response);
			return;
		} finally {
			try {
				if(result != null){
					result.close();
				}
				prepStmt.close();
				conn.close();
			} catch (SQLException e) {
				//SQLException caught
			}
			
		}
    	
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
			
			HttpSession session = request.getSession();
    		User curr = (User)session.getAttribute("curr");
    		if(curr == null){
    			request.setAttribute("success", false);
				request.setAttribute("message", "Session timed-out!");
    			request.getRequestDispatcher("/src/login.jsp").forward(request, response);
    		} else {
	    		prepStmt = conn.prepareStatement("SELECT * FROM top_housing_table LEFT JOIN housing_table ON top_housing_table.housingID = housing_table.keyid"
						+ " WHERE top_housing_table.userID = ?");
				prepStmt.setInt(1, curr.getUserID());
				result = prepStmt.executeQuery();
	    		
				//redirecting pages
				request.setAttribute("top-housing", result);
	    		request.getRequestDispatcher("/src/top-housing.jsp").forward(request, response);
	    		return;
    		}	

			/********************************************/
			
    	} catch (SQLException e) {
			request.setAttribute("success", false);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/src/top-housing.jsp").forward(request, response);
			return;
		} catch (ClassNotFoundException e) {
			request.setAttribute("success", false);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/src/top-housing.jsp").forward(request, response);
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
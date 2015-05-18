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

@WebServlet(name="RoommatesController", urlPatterns="/roommates")
public class RoommatesController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public RoommatesController(){
		//constructor
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
    			return;
    		} else {
				prepStmt = conn.prepareStatement("SELECT * FROM roommate_table WHERE userID = ?");
				prepStmt.setInt(1, curr.getUserID());
				//prepStmt.setInt(2,  curr.getUserID());
				result = prepStmt.executeQuery();
				
//				//querying the prefs of the users
//				PreparedStatement prefsQuery = conn.prepareStatement("SELECT * FROM preference_table INNER JOIN roommates_table WHERE ... = ?");
//	    		prefsQuery.setInt(1, Integer.parseInt(request.getParameter("housingID")));
//				ResultSet roommatePrefs = prefsQuery.executeQuery();   		
//				request.setAttribute("roommate-prefs", roommatePrefs);
				
				request.setAttribute("user-roommates", result);
//				request.setAttribute("success", true);
//				request.setAttribute("message", "Session timed-out!");
    			request.getRequestDispatcher("/src/roommates.jsp").forward(request, response);
    			return;
    		}
	
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
    			return;
    		} else {
				prepStmt = conn.prepareStatement("SELECT * FROM roommate_table WHERE requesteeID = ? OR requesterID = ?");
				prepStmt.setInt(1, curr.getUserID());
				prepStmt.setInt(2,  curr.getUserID());
				result = prepStmt.executeQuery();
				
//				//querying the prefs of the users
//				PreparedStatement prefsQuery = conn.prepareStatement("SELECT * FROM preference_table INNER JOIN roommates_table WHERE ... = ?");
//	    		prefsQuery.setInt(1, Integer.parseInt(request.getParameter("housingID")));
//				ResultSet roommatePrefs = prefsQuery.executeQuery();   		
//				request.setAttribute("roommate-prefs", roommatePrefs);
				
				request.setAttribute("user-roommates", result);
//				request.setAttribute("success", true);
//				request.setAttribute("message", "Session timed-out!");
    			request.getRequestDispatcher("/src/roommates.jsp").forward(request, response);
    			return;
    		}
	
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
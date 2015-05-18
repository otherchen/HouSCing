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

@WebServlet(name="HousingController", urlPatterns="/add-review")
public class HousingController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public HousingController(){
		//constructor
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	//redirect to intended page
    	request.getRequestDispatcher("/src/housing.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	Connection conn = null;
		PreparedStatement prepStmt = null;
		//ResultSet result = null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/csci201_database?user=root&password=212340Cba");		
			
			/********************************************/
					  // Do all logic here! //			
			
    		//saving the user's id and the housing id in the top_housing_table
    		HttpSession session = request.getSession();
    		User curr = (User)session.getAttribute("curr");
    		if(curr == null){
    			request.setAttribute("success", false);
				request.setAttribute("message", "Session timed-out!");
    			request.getRequestDispatcher("/src/login.jsp").forward(request, response);
    			return;
    		} else {
	    		prepStmt = conn.prepareStatement("INSERT INTO reviews_table(housingID, stars, content, author, reviewDate)"
						+ " VALUES(?, ?, ?, ?, ?)");
	    		prepStmt.setInt(1, Integer.parseInt(request.getParameter("housingID")));
	    		prepStmt.setInt(2, Integer.parseInt(request.getParameter("review-stars")));
				prepStmt.setString(3, request.getParameter("review-content"));
				prepStmt.setString(4, curr.getFullName());
				
				//storing the current date in object
				java.util.Date today = new java.util.Date();
				long time = today.getTime();
				
				//storing the current date into MySQL
				prepStmt.setDate(5, new java.sql.Date(time));
				prepStmt.executeUpdate();
				
				System.out.println("Successful!");
		
				request.setAttribute("success", true);
				request.setAttribute("message", "Reviewed Housing!");
	    		request.getRequestDispatcher("/review-housing").forward(request, response);
	    		return;
    		}
			/********************************************/
			
    	} catch (SQLException e) {
			request.setAttribute("success", false);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/src/housing.jsp").forward(request, response);
			return;
		} catch (ClassNotFoundException e) {
			request.setAttribute("success", false);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/src/housing.jsp").forward(request, response);
			return;
		} finally {
			try {
				//result.close();
				prepStmt.close();
				conn.close();
			} catch (SQLException e) {
				//SQLException caught
			}
			
		}
    	
    }
    
}
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

@WebServlet(name="MethodController", urlPatterns={"/save-housing", "/remove-housing", "/review-housing", "/user-request", "/user-chat"})
public class MethodController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public MethodController(){
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
			
			String userPath = request.getServletPath();

	    	if(userPath.equals("/save-housing")){
	    		//1. save the user's id and the housing id as a row in the top-housing table
	    		//2. save the top-housing to the user's top-housing array list
	    		//3. redirect back to the previous page with the same page status/content!
	    		//4. if housing is already saved, disable the button or something
	    		
	    		//saving the user's id and the housing id in the top_housing_table
	    		HttpSession session = request.getSession();
	    		User curr = (User)session.getAttribute("curr");
	    		if(curr == null){
	    			request.setAttribute("success", false);
					request.setAttribute("message", "Session timed-out!");
	    			request.getRequestDispatcher("/src/login.jsp").forward(request, response);
	    		} else {
	    			
	    			prepStmt = conn.prepareStatement("SELECT * FROM top_housing_table WHERE userID = ? AND housingID = ?");
	    			prepStmt.setInt(1, curr.getUserID());
	    			prepStmt.setInt(2, Integer.parseInt(request.getParameter("housingID")));
	    			result = prepStmt.executeQuery();
	    			
	    			if(result.isBeforeFirst()){
	    				request.setAttribute("success", false);
						request.setAttribute("message", "Housing is already saved!");
			    		request.getRequestDispatcher("/src/search.jsp").forward(request, response);
			    		return;
	    			} else {
	    			
			    		PreparedStatement saveStmt = conn.prepareStatement("INSERT INTO top_housing_table(userID, housingID)"
								+ " VALUES(?, ?)");
						saveStmt.setInt(1, curr.getUserID());
						saveStmt.setInt(2, Integer.parseInt(request.getParameter("housingID")));
						saveStmt.executeUpdate();
			    		
						//setting the alert messages
						request.setAttribute("success", true);
						request.setAttribute("message", "Saved to Top Housing!");
			    		request.getRequestDispatcher("/src/search.jsp").forward(request, response);
			    		return;
	    			}
	    		}
	    	} else if (userPath.equals("/remove-housing")){
	    		//remove the row from the top_housing_table
	    		HttpSession session = request.getSession();
	    		User curr = (User)session.getAttribute("curr");
	    		prepStmt = conn.prepareStatement("DELETE FROM top_housing_table WHERE userID = ? AND housingID = ?");
				prepStmt.setInt(1, curr.getUserID());
				prepStmt.setInt(2, Integer.parseInt(request.getParameter("housingID")));
				prepStmt.executeUpdate();
	    		
				//setting the alert messages
				request.setAttribute("success", true);
				request.setAttribute("message", "Removed Top Housing!");
	    		request.getRequestDispatcher("/top-housing").forward(request, response);
	    		return;
	    	} else if (userPath.equals("/review-housing")){
	    		//1. redirect to the review housing page populated with the current housing info
	    		prepStmt = conn.prepareStatement("SELECT * FROM housing_table WHERE keyid = ?");
				prepStmt.setInt(1, Integer.parseInt(request.getParameter("housingID")));
				result = prepStmt.executeQuery();
				
				//querying the reviews of the housing
				PreparedStatement reviewStmt = conn.prepareStatement("SELECT * FROM reviews_table WHERE housingID = ?");
	    		reviewStmt.setInt(1, Integer.parseInt(request.getParameter("housingID")));
				ResultSet reviews = reviewStmt.executeQuery();
	    		
				request.setAttribute("housingReviews", reviews);
				request.setAttribute("housingInfo", result);
	    		request.getRequestDispatcher("/src/housing.jsp").forward(request, response);
	    		return;
	    	} else if (userPath.equals("/user-request")){
	    		//1. check whether to accept room mate or request room mate
	    		//2. add the room mates to the roommate_table and change the status to pending or accepted
	    		//3. use networking/multi-threading to send a signal to the other computer and have notification pop up
	    		//4. call the User.request, User.accept methods.
	    		
	    		request.setAttribute("success", true);
				request.setAttribute("message", "Requested user!");
	    		request.getRequestDispatcher("/src/user.jsp").forward(request, response);
	    		return;
	    	} else if (userPath.equals("/user-chat")){
	    		//1. check who the two "users" are
	    		//2. open up a chat dialog (separate or within the same window?)
	    		//3. call the UserChat method
	    		
	    		request.setAttribute("success", true);
				request.setAttribute("message", "Initiated chat successfully!");
	    		request.getRequestDispatcher("/src/user.jsp").forward(request, response);
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
}
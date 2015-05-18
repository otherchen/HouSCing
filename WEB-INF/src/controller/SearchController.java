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

@WebServlet(name="SearchController", urlPatterns={"/search"})
public class SearchController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public SearchController(){
		//constructor
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	//redirect to intended page
    	request.getRequestDispatcher("/src/search.jsp").forward(request, response);
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
			String keyword = request.getParameter("keyword");
			if(request.getParameter("search-type").equals("user")){
				
				prepStmt = conn.prepareStatement("SELECT * FROM user_table WHERE email = ?");
    			prepStmt.setString(1, keyword);
    			result = prepStmt.executeQuery();
    			
    			//checks if the user's keyword was an email address
    			if(result.isBeforeFirst() ){
    				//save the queried result
    				request.setAttribute("search-type", "user");
    				request.setAttribute("queried-result", result);	
    				
    				//query for the user preferences now
    				result.next();
    				PreparedStatement query = conn.prepareStatement("SELECT * FROM preference_table WHERE userID = ?");
        			query.setInt(1, result.getInt("userID"));
        			ResultSet queryResult = query.executeQuery();
        			request.setAttribute("user-prefs", queryResult); 
        			result.beforeFirst();
        			
    				//return and set messages
        			request.setAttribute("success", true);
					request.setAttribute("message", "Returned search by email!");
					request.getRequestDispatcher("/src/search.jsp").forward(request, response);
					queryResult.close();
        			query.close();
					return;						
    			//if its not, then try querying for housing
    			} else {
    				request.setAttribute("success", false);
					request.setAttribute("message", "No user by that email!");
					request.getRequestDispatcher("/src/search.jsp").forward(request, response);
					return;
    			}  			
    			
			} else if (request.getParameter("search-type").equals("housing")){
				prepStmt = conn.prepareStatement("SELECT * FROM housing_table WHERE address LIKE ?");
				prepStmt.setString(1, "%" + keyword + "%");
				result = prepStmt.executeQuery();
				
				if(result.isBeforeFirst()){
					request.setAttribute("search-type", "housing");
					request.setAttribute("queried-result", result);
					//return and set messages
    				request.setAttribute("success", true);
					request.setAttribute("message", "Returned search by address!");
					request.getRequestDispatcher("/src/search.jsp").forward(request, response);
					return;
				} else {
					request.setAttribute("success", false);
					request.setAttribute("message", "No housing by that address!");
					request.getRequestDispatcher("/src/search.jsp").forward(request, response);
					return;
				}
			}
	
			/********************************************/
			
    	} catch (SQLException e) {
			request.setAttribute("success", false);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/src/search.jsp").forward(request, response);
			return;
		} catch (ClassNotFoundException e) {
			request.setAttribute("success", false);
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/src/search.jsp").forward(request, response);
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

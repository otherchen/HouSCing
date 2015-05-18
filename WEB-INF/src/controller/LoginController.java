package controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Preference;
import model.User;

@WebServlet(name="LoginController", urlPatterns={"/login", "/signup", "/profile", "/logout"})
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Connection conn;
	
	public LoginController(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/csci201_database?user=root&password=212340Cba");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	
    	String userPath = request.getServletPath();

    	if(userPath.equals("/login")){
    		 request.getRequestDispatcher("/src/login.jsp").forward(request, response);
    	} else if (userPath.equals("/signup")){
    		 request.getRequestDispatcher("/src/signup.jsp").forward(request, response);
    	} else if (userPath.equals("/profile")){
    		//redirecting to login if user hasn't logged in yet
			HttpSession session = request.getSession(); 
    		if(session.getAttribute("curr") == null){
    			request.setAttribute("success", false);
				request.setAttribute("message", "Session timed-out!");
    			request.getRequestDispatcher("/src/login.jsp").forward(request, response);
    		} else {
    			request.getRequestDispatcher("/src/profile.jsp").forward(request, response);
    		}
    	} else if (userPath.equals("/logout")){
    		HttpSession session = request.getSession(); 
    		User currUser = (User)session.getAttribute("curr");
			session.setAttribute("curr", null);
			request.setAttribute("success", true);
			request.setAttribute("message", "Logged out!");
			
			//updating the online_table
			try{
				PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM online_table WHERE userID = ?");
				prepStmt.setInt(1, currUser.getUserID());
				prepStmt.executeUpdate();
			} catch (SQLException sqe){
				request.setAttribute("success", false);
				request.setAttribute("message", sqe.getMessage());
			}
			
    		request.getRequestDispatcher("/src/login.jsp").forward(request, response);
    	} 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    	//finding out where form submission came from
    	String userPath = request.getServletPath();

    	//if the user submits to the login page
    	if(userPath.equals("/login")){
    		try {
    			//check if email/user exists
    			PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM user_table WHERE email = ?");
    			prepStmt.setString(1, request.getParameter("email"));
    			ResultSet result = prepStmt.executeQuery();
    			
    			if(result.next()){	
    				String tempPass = result.getString("password");
    				if(tempPass.equals(request.getParameter("pass"))){
    					
    					/***************************************************/
    					//Create user object and log the user in
    					//store the user object in the session
    					/***************************************************/
    					User loggedInUser = new User();
    					loggedInUser.setVars(
    						result.getInt("userID"),
    						result.getString("firstName"),
    						result.getString("lastName"),
    						result.getString("email"),
    						result.getString("password")
    					);
    					
    					prepStmt = conn.prepareStatement("SELECT * FROM preference_table WHERE userID = ?");
    					prepStmt.setInt(1, loggedInUser.getUserID());
    					result = prepStmt.executeQuery();
    					result.next();
    					
    					Preference prefs = loggedInUser.getPrefs();
    					prefs.setRoomType(result.getString("preferredRoomType"));
    					prefs.setCookFrequency(result.getInt("cookFrequency"));
    					prefs.setCleanliness(result.getInt("cleanliness"));
    					prefs.setPartyFrequency(result.getInt("partyFrequency"));
    					prefs.setGuestFrequency(result.getInt("guestFrequency"));
    					prefs.setMusicLoudness(result.getInt("musicLoudness"));
    					prefs.setStudyFrequency(result.getInt("studyFrequency"));
    					prefs.setSleepingTime(result.getTime("sleepingTime"));
    					prefs.setWakingTime(result.getTime("wakingTime"));
    					prefs.setSchoolYear(result.getString("schoolYear"));
    					prefs.setPets(result.getBoolean("pets"));
    					prefs.setSmokes(result.getBoolean("smokes"));
    					prefs.setGreek(result.getBoolean("greek"));
    					prefs.setPrivacy(result.getInt("privacy"));
    					prefs.setHobbies(result.getString("hobbies"));
    					prefs.setFunFact(result.getString("funFact"));
    					
    					//saving user in the session
    					HttpSession session = request.getSession(); 
    					session.setAttribute("curr", loggedInUser);
    					session.setMaxInactiveInterval(900);
    					
    					//updating the online_table
    					prepStmt = conn.prepareStatement("INSERT INTO online_table(userID, firstName, lastName)"
							+ " VALUES(?, ?, ?)");
    					prepStmt.setInt(1, loggedInUser.getUserID());
    					prepStmt.setString(2, loggedInUser.getFirstName());
    					prepStmt.setString(3, loggedInUser.getLastName());
    					prepStmt.executeUpdate();
    					
    					result.close();
    					prepStmt.close();
    					request.setAttribute("success", true);
    					request.setAttribute("message", "Welcome to Housing!");
    					request.getRequestDispatcher("/src/search.jsp").forward(request, response);
    					
    					return;
    				} else {
    					//password is incorrect
    					result.close();
    					prepStmt.close();
    					request.setAttribute("success", false);
    					request.setAttribute("message", "Email or password is incorrect!");
    					request.getRequestDispatcher("/src/login.jsp").forward(request, response);
    					return;
    				}	
    			} else {
    				//if the query doesn't return anything
    				result.close();
    				prepStmt.close();
    				request.setAttribute("success", false);
    				request.setAttribute("message", "Email does not exist!");
    				request.getRequestDispatcher("/src/login.jsp").forward(request, response);
    				return;
    			}
    			
        	} catch (SQLException e) {
        		request.setAttribute("success", false);
				request.setAttribute("message", e.getMessage());
				request.getRequestDispatcher("/src/login.jsp").forward(request, response);
				return;
    		} 

    	//if the user submits to the sign up page
    	} else if (userPath.equals("/signup")){
    		try {
	    		PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM user_table WHERE email = ?");
				prepStmt.setString(1, request.getParameter("email"));
				ResultSet result = prepStmt.executeQuery();
				
				if(result.next()){	
					result.close();
    				prepStmt.close();
    				request.setAttribute("success", false);
    				request.setAttribute("message", "Email already exists!");
    				request.getRequestDispatcher("/src/signup.jsp").forward(request, response);
    				return;
				} else {
					
					if(!request.getParameter("pass").equals(request.getParameter("pass-confirm"))){
						result.close();
	    				prepStmt.close();
	    				request.setAttribute("success", false);
	    				request.setAttribute("message", "Passwords do not match!");
	    				request.getRequestDispatcher("/src/signup.jsp").forward(request, response);
	    				return;
					}
					
					//inserting the user data into the database
					prepStmt = conn.prepareStatement("INSERT INTO user_table(firstName, lastName, email, password)"
							+ " VALUES(?, ?, ?, ?)");
					prepStmt.setString(1, request.getParameter("first-name"));
					prepStmt.setString(2, request.getParameter("last-name"));
					prepStmt.setString(3, request.getParameter("email"));
					prepStmt.setString(4, request.getParameter("pass"));
					prepStmt.executeUpdate();
					
					//getting the userID of the row just created
					prepStmt = conn.prepareStatement("SELECT userID FROM user_table WHERE email = ?");
					prepStmt.setString(1,  request.getParameter("email"));
					ResultSet userIDQuery = prepStmt.executeQuery();
					userIDQuery.next();
					
					//creating a row for the user in preference_table
					prepStmt = conn.prepareStatement("INSERT INTO preference_table("
							+ "userID,"
							+ "preferredRoomType,"
							+ "cookFrequency,"
							+ "cleanliness,"
							+ "partyFrequency,"
							+ "guestFrequency,"
							+ "musicLoudness,"
							+ "studyFrequency,"
							+ "sleepingTime,"
							+ "wakingTime,"
							+ "schoolYear,"
							+ "pets,"
							+ "smokes,"
							+ "greek,"
							+ "privacy,"
							+ "hobbies,"
							+ "funFact)"
							+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					prepStmt.setInt(1, userIDQuery.getInt("userID"));
					prepStmt.setString(2, "");
					prepStmt.setInt(3, 5);
					prepStmt.setInt(4, 5);
					prepStmt.setInt(5, 5);
					prepStmt.setInt(6, 5);
					prepStmt.setInt(7, 5);
					prepStmt.setInt(8, 5);
					prepStmt.setTime(9, Time.valueOf("00:00:00"));
					prepStmt.setTime(10, Time.valueOf("12:00:00"));
					prepStmt.setString(11, "");
					prepStmt.setBoolean(12, false);
					prepStmt.setBoolean(13, false);
					prepStmt.setBoolean(14, false);
					prepStmt.setInt(15, 5);
					prepStmt.setString(16, "");
					prepStmt.setString(17, "");
					prepStmt.executeUpdate();
						
					//closing streams and redirecting
					result.close();
					prepStmt.close();
					request.setAttribute("success", true);
					request.setAttribute("message", "Sign up successful!");
					request.getRequestDispatcher("/src/login.jsp").forward(request, response);
					return;			
				}
				
    		} catch (SQLException e){
				request.setAttribute("success", false);
				request.setAttribute("message", e.getMessage());
				request.getRequestDispatcher("/src/signup.jsp").forward(request, response);
				return;			
    		}
    		
    	} else if (userPath.equals("/profile")){
    		
    		/*********************************************/
    		/*********     Save Profile info      ********/
    		/*********************************************/
    		
    		//first page info
    		String roomType = request.getParameter("room-type");
    		int cookFrequency = Integer.parseInt(request.getParameter("cook-frequency"));
    		int cleanliness = Integer.parseInt(request.getParameter("clean-frequency"));
    		int partyFrequency = Integer.parseInt(request.getParameter("party-frequency"));
    		int guestFrequency = Integer.parseInt(request.getParameter("guest-frequency"));
    		
    		//second page info
    		String schoolYear = request.getParameter("school-year");
    		int studyFrequency = Integer.parseInt(request.getParameter("study-frequency"));
    		int musicLoudness = Integer.parseInt(request.getParameter("music-loudness"));
    		//storing the sleep/wake time
    		Date sleepDate = null;
    		Date wakeDate = null;
			try {
				sleepDate = new SimpleDateFormat("HH:mm").parse(request.getParameter("sleep-time"));
				wakeDate = new SimpleDateFormat("HH:mm").parse(request.getParameter("wake-time"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
    		Time sleepTime = new Time(sleepDate.getTime());
    		Time wakeTime = new Time(wakeDate.getTime());
    		
    		//third page info
    		boolean smokes = false;
    		boolean pets = false;
    		boolean greek = false;
    		if(request.getParameter("smokes") != null && request.getParameter("smokes").equals("smokes")){
    			smokes = true;
    		}
    		if(request.getParameter("pets") != null && request.getParameter("pets").equals("pets")){
    			pets = true;
    		}
    		if(request.getParameter("greek") != null && request.getParameter("greek").equals("greek")){
    			greek = true;
    		}
    		int privacy = Integer.parseInt(request.getParameter("privacy"));
    		String hobbies = request.getParameter("hobbies");
    		String funFact = request.getParameter("fun-fact");
    		
    		/******************************************/
    		//saving all data in user's pref obj and MySQL
    		/******************************************/	 
    		
    		HttpSession session = request.getSession(); 
    		User currUser = (User)session.getAttribute("curr");
    		if(currUser == null){
    			request.setAttribute("success", false);
				request.setAttribute("message", "Session timed-out!");
    			request.getRequestDispatcher("/src/login.jsp").forward(request, response);
    			return;
    		} else {
    			
    			//save the preferences in the user object
    			Preference prefs = currUser.getPrefs();
    			
				prefs.setRoomType(roomType);
				prefs.setCookFrequency(cookFrequency);
				prefs.setCleanliness(cleanliness);
				prefs.setPartyFrequency(partyFrequency);
				prefs.setGuestFrequency(guestFrequency);
				prefs.setMusicLoudness(musicLoudness);
				prefs.setStudyFrequency(studyFrequency);
				prefs.setSleepingTime(sleepTime);
				prefs.setWakingTime(wakeTime);
				prefs.setSchoolYear(schoolYear);
				prefs.setPets(pets);
				prefs.setSmokes(smokes);
				prefs.setGreek(greek);
				prefs.setPrivacy(privacy);
				prefs.setHobbies(hobbies);
				prefs.setFunFact(funFact);
    			
        		//save the preferences in MySQL
        		try{       			
    	    		PreparedStatement prepStmt = conn.prepareStatement("UPDATE preference_table SET "
    	    				+ "preferredRoomType = ?,"
    	    				+ "cookFrequency = ?,"
    	    				+ "cleanliness = ?,"
    	    				+ "partyFrequency = ?,"
    	    				+ "guestFrequency = ?,"
    	    				+ "musicLoudness = ?,"
    	    				+ "studyFrequency = ?,"
    	    				+ "sleepingTime = ?,"
    	    				+ "wakingTime = ?,"
    	    				+ "schoolYear = ?,"
    	    				+ "pets = ?,"
    	    				+ "smokes = ?,"
    	    				+ "greek = ?,"
    	    				+ "privacy = ?,"
    	    				+ "hobbies = ?,"
    	    				+ "funFact = ? "
    	    				+ "WHERE userID = ?");
					prepStmt.setString(1, roomType);
					prepStmt.setInt(2, cookFrequency);
					prepStmt.setInt(3, cleanliness);
					prepStmt.setInt(4, partyFrequency);
					prepStmt.setInt(5, guestFrequency);
					prepStmt.setInt(6, musicLoudness);
					prepStmt.setInt(7, studyFrequency);
					prepStmt.setTime(8, sleepTime);
					prepStmt.setTime(9, wakeTime);
					prepStmt.setString(10, schoolYear);
					prepStmt.setBoolean(11, pets);
					prepStmt.setBoolean(12, smokes);
					prepStmt.setBoolean(13, greek);
					prepStmt.setInt(14, privacy);
					prepStmt.setString(15, hobbies);
					prepStmt.setString(16, funFact);
					prepStmt.setInt(17, ((User)session.getAttribute("curr")).getUserID());
					prepStmt.executeUpdate();
    				
    				//closing streams and redirecting
    				prepStmt.close();
    				request.setAttribute("success", true);
    				request.setAttribute("message", "Save successful!");
    				request.getRequestDispatcher("/src/profile.jsp").forward(request, response);
    				return;	
        		} catch (SQLException sqle){
        			request.setAttribute("success", false);
    				request.setAttribute("message", sqle.getMessage());
    				request.getRequestDispatcher("/src/profile.jsp").forward(request, response);
    				return;	 
        		}
    		
    		} 
    		  		
    	} // end of profile segment

    }

}

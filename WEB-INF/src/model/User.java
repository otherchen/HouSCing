package model;

import java.util.ArrayList;

public class User {
	int userID;
	String firstName;
	String lastName;
	String email;
	String password;
	ArrayList<Integer> requestedRoommates;
	ArrayList<Integer> pendingRoommates;
	ArrayList<Integer> currentRoommates;
	UserChat chat;
	Preference prefs;
	
	public User(){
		//constructor
		userID = 0;
		firstName = null;
		lastName = null;
		email = null;
		password = null;
		requestedRoommates = new ArrayList<Integer>();
		pendingRoommates = new ArrayList<Integer>();
		currentRoommates = new ArrayList<Integer>();
		chat = new UserChat();
		prefs = new Preference();
	}
	
	public String encryptPassword(String pass){
		//perform some sort of encryption algorithm
		return pass;
	}
	
	public boolean requestRoommate(int userID){
		//returns true if successful, false if not
		return true;
	}
	
	public boolean acceptRoommate(int userID){
		//returns true if successful, false if not
		return true;
	}
	
	public void setVars(int id, String fName, String lName, String eml, String pass){
		userID = id;
		firstName = fName;
		lastName = lName;
		email = eml;
		password = pass;
	}
	
	public Preference getPrefs(){
		return prefs;
	}
	
	public int getUserID(){
		return this.userID;
	}
	
	public String getFullName(){
		return firstName + " " + lastName;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	//add any getter & setter methods needed
	
}

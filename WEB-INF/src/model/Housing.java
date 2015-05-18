package model;

import java.util.ArrayList;

public class Housing {
	
	int housingID;
	String address;
	int bed;
	int bath;
	int rent;
	int wifi;
	int ac;
	int parking;
	int cable;
	double latitude;
	double longitude;
	double distance;
	int minutes;
	int uscOwned;
	int visibilityCounter;
	int sameFilterCheck;
	ArrayList<Review> reviews;
	
	public Housing(){
		//constructor
		housingID = -1;
		address = null;
		bed = -1;
		bath = -1;
		rent = -1;
		wifi = -1;
		ac = -1;
		parking = -1;
		cable = -1;
		latitude = -1;
		longitude = -1;
		distance = -1;
		minutes = -1;
		uscOwned = -1;
		visibilityCounter = 0;
		sameFilterCheck = 0;
		reviews = new ArrayList<Review>();
	}
	
	public void addReview(Review rev){
		//add a review to the housing obj
	}
	
	//add any getters & setters needed
	
}

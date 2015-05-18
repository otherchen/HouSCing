package model;

import java.sql.Time;

public class Preference {
	
	String preferredRoomType;
	int cookFrequency;
	int cleanliness;
	int partyFrequency;
	int guestFrequency;
	int musicLoudness;
	int studyFrequency;
	Time sleepingTime;
	Time wakingTime;
	String schoolYear;
	boolean pets;
	boolean smokes;
	boolean greek;
	int privacy;
	String hobbies;
	String funFact;
	
	public Preference(){
		//constructor
		preferredRoomType = "";
		cookFrequency = 5;
		cleanliness = 5;
		partyFrequency = 5;
		guestFrequency = 5;
		musicLoudness = 5;
		studyFrequency = 5;
		sleepingTime = Time.valueOf("00:00:00");
		wakingTime = Time.valueOf("12:00:00");
		schoolYear = "";
		pets = false;
		smokes = false;
		greek = false;
		privacy = 5;
		hobbies = "";
		funFact = "";
	}
	
	public int compare(Preference otherPref){
		//returns a heuristic value used to estimate how similar
		//your living patterns are to another user
		return -1;
	}
	
	public String getRoomType(){
		return this.preferredRoomType;
	}
	
	public void setRoomType(String roomType){
		this.preferredRoomType = roomType;
	}
	
	public int getCookFrequency(){
		return this.cookFrequency;
	}
	
	public void setCookFrequency(int cookFreq){
		this.cookFrequency = cookFreq;
	}
	
	public int getCleanliness(){
		return this.cleanliness;
	}
	
	public void setCleanliness(int clean){
		this.cleanliness = clean;
	}
	
	public int getPartyFrequency(){
		return this.partyFrequency;
	}
	
	public void setPartyFrequency(int party){
		this.partyFrequency = party;
	}
	
	public int getGuestFrequency(){
		return this.guestFrequency;
	}
	
	public void setGuestFrequency(int guest){
		this.guestFrequency = guest;
	}

	public int getMusicLoudness(){
		return this.musicLoudness;
	}
	
	public void setMusicLoudness(int musicLoud){
		this.musicLoudness = musicLoud;
	}
	
	public int getStudyFrequency(){
		return this.studyFrequency;
	}
	
	public void setStudyFrequency(int studyFreq){
		this.studyFrequency = studyFreq;
	}
	
	public Time getSleepingTime(){
		return this.sleepingTime;
	}
	
	public void setSleepingTime(Time sleepTime){
		this.sleepingTime = sleepTime;
	}
	
	public Time getWakingTime(){
		return this.wakingTime;
	}
	
	public void setWakingTime(Time wakeTime){
		this.wakingTime = wakeTime;
	}
	
	public String getSchoolYear(){
		return this.schoolYear;
	}
	
	public void setSchoolYear(String grade){
		this.schoolYear = grade;
	}
	
	public boolean getPets(){
		return this.pets;
	}
	
	public void setPets(boolean hasPets){
		this.pets = hasPets;
	}
	
	public boolean getSmokes(){
		return this.smokes;
	}
	
	public void setSmokes(boolean doesSmoke){
		this.smokes = doesSmoke;
	}
	
	public boolean getGreek(){
		return this.greek;
	}
	
	public void setGreek(boolean isGreek){
		this.greek = isGreek;
	}
	
	public int getPrivacy(){
		return this.privacy;
	}
	
	public void setPrivacy(int priv){
		this.privacy = priv;
	}
	
	public String getHobbies(){
		return this.hobbies;
	}
	
	public void setHobbies(String text){
		this.hobbies = text;
	}
	
	public String getFunFact(){
		return this.funFact;
	}
	
	public void setFunFact(String text){
		this.funFact = text;
	}
	
}

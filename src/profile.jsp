<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8">

  <title>Profile</title>

  <link href="lib/css/bootstrap.css" rel="stylesheet">
  <link href="lib/css/navbar.css" rel="stylesheet">
  <link href="lib/css/profile.css" rel="stylesheet">
  <script src="lib/js/jquery-1.11.2.min.js"> </script>
  <script src="lib/js/bootstrap.js"> </script>

</head>

<body>

<%@ page import="model.Preference, model.User" %>
<%@ include file="navbar.jsp" %>

<%
User curr = (User)session.getAttribute("curr"); 
Preference currPrefs = curr.getPrefs();
%>

  <!-- multistep form -->
<form id="msform" action="/Housing/profile" method="post">
	<!-- progressbar -->
	<ul id="progressbar">
		<li class="active">Living Style</li>
		<li>Study Habits</li>
		<li>Personal Secrets</li>
	</ul>
	<!-- fieldsets -->
	<fieldset>
		<h2 class="fs-title">Living Style</h2>
		<!--  <h3 class="fs-subtitle">What I Want</h3 -->		
		<hr/>
		
		<div class="text-left">
		<span>What type of room do you prefer?</span>
		<div class = "form-group"></div>
		<select class="form-control" name="room-type">
	    	<option value="single"
		    <% if(currPrefs.getRoomType() != null && ((String)currPrefs.getRoomType()).equals("single")){ %>
				selected="selected"
			<% } %>
	    	>Single</option>
	    	
	    	<option value="double"
	    	<% if(currPrefs.getRoomType() != null && ((String)currPrefs.getRoomType()).equals("double")){ %>
				selected="selected"
			<% } %>
	    	>Double</option>
	    	
	    	<option value="triple"
	    	<% if(currPrefs.getRoomType() != null && ((String)currPrefs.getRoomType()).equals("triple")){ %>
				selected="selected"
			<% } %>
	    	>Triple</option> 	
		</select>
		
		<br/>		
		<span>How often do you cook?</span>
		<input type="range" class="form-group" name="cook-frequency" min="0" max="10" value=<%= "" + currPrefs.getCookFrequency() %> />
		<span>How clean and tidy are you?</span>
		<input type="range" class="form-group" name="clean-frequency" min="0" max="10" value=<%= "" + currPrefs.getCleanliness() %> />
		<span>How often are you out late/party?</span>
		<input type="range" class="form-group" name="party-frequency" min="0" max="10" value=<%= "" + currPrefs.getPartyFrequency() %> />
		<span>How often do you have guests over?</span>
		<input type="range" class="form-group" name="guest-frequency" min="0" max="10" value=<%= "" + currPrefs.getGuestFrequency() %> />
		</div>
		
		<input type="button" name="next" class="next action-button" value="Next" />
		
	</fieldset>
	<fieldset>
		<h2 class="fs-title">Study Habits</h2>
	<!-- 	<h3 class="fs-subtitle">Things I Do</h3> -->
		<hr/>
		
		<div class="text-left">
		<span>What is your grade level?</span>
		<div class = "form-group"></div>
		<select class="form-control" name="school-year">
	    	<option value="freshman"
	    	<% if(currPrefs.getSchoolYear() != null && ((String)currPrefs.getSchoolYear()).equals("freshman")){ %>
				selected="selected"
			<% } %>
	    	>Freshman</option>
	    	<option value="sophomore"
	    	<% if(currPrefs.getSchoolYear() != null && ((String)currPrefs.getSchoolYear()).equals("sophomore")){ %>
				selected="selected"
			<% } %>
	    	>Sophomore</option>
	    	<option value="junior"
	    	<% if(currPrefs.getSchoolYear() != null && ((String)currPrefs.getSchoolYear()).equals("junior")){ %>
				selected="selected"
			<% } %>
	    	>Junior</option>
	    	<option value="senior"
	    	<% if(currPrefs.getSchoolYear() != null && ((String)currPrefs.getSchoolYear()).equals("senior")){ %>
				selected="selected"
			<% } %>
	    	>Senior</option>
	    	<option value="graduate"
	    	<% if(currPrefs.getSchoolYear() != null && ((String)currPrefs.getSchoolYear()).equals("graduate")){ %>
				selected="selected"
			<% } %>
	    	>Graduate</option>
		</select>	
		<br/>
		<span>What time do you sleep?</span>
		<input type="time" class="time" name="sleep-time" value=<%= currPrefs.getSleepingTime() %> />		
		<br/>
		<span>What time do you wake up?</span>
		<input type="time" class="time" name="wake-time" value=<%= currPrefs.getWakingTime() %> />		
		<br/>		
		<span>How often do you plan on studying in the room?</span>
		<input type="range" class="form-group" name="study-frequency" min="0" max="10" value=<%= "" + currPrefs.getStudyFrequency() %> />
		<span>How loud would you like music playing?</span>
		<input type="range" class="form-group" name="music-loudness" min="0" max="10" value=<%= "" + currPrefs.getMusicLoudness() %> />
		</div>
		
		<input type="button" name="previous" class="previous action-button" value="Previous" />
		<input type="button" name="next" class="next action-button" value="Next" />
	</fieldset>
	<fieldset>
		<h2 class="fs-title">Personal Secrets</h2>
	<!-- 	<h3 class="fs-subtitle">This is Me</h3>-->
		<hr/>
		
		<div class="checkbox-container">
			<div class="left">
				<label class="checkbox-label">
					<input type="checkbox" class="checkbox" value="smokes" name="smokes"
					<% if((Boolean)currPrefs.getSmokes()){ %>
						checked
					<% } %>
					/> I smoke
				</label>
			</div>
			<div class="right">
				<label class="checkbox-label">
					<input type="checkbox" class="checkbox" value="greek" name="greek"
					<% if((Boolean)currPrefs.getGreek()){ %>
						checked
					<% } %>
					/> I am Greek
				</label>
			</div>
			<div class="center">
				<label class="checkbox-label">
					<input type="checkbox" class="checkbox" value="pets" name="pets"
					<% if((Boolean)currPrefs.getPets()){ %>
						checked
					<% } %>
					/> I have pets
				</label>
			</div>
		</div>
		
		<br/>
		<div class="text-left">
		<span>How willing are you to share your belongings?</span>
		<input type="range" class="form-group" name="privacy" min="0" max="10" value=<%= currPrefs.getPrivacy() %> />
		<textarea name="hobbies" placeholder="I enjoy/I am a part of..."><%= currPrefs.getHobbies() %></textarea>
		<textarea name="fun-fact" placeholder="Something fun about me is that I..."><%= currPrefs.getFunFact() %></textarea>
		</div>
		
		<input type="button" name="previous" class="previous action-button" value="Previous" />
		<input type="submit" class="action-button" value="Save" />
	</fieldset>
</form>

<!-- jQuery -->

<script src="http://thecodeplayer.com/uploads/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
<!-- jQuery easing plugin --> 
<script src="http://thecodeplayer.com/uploads/js/jquery.easing.min.js" type="text/javascript"></script> 
<script src="lib/js/profile.js"></script>

</body>

</html>
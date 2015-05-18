<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8">

  <title>Search</title>

  <link href="lib/css/bootstrap.css" rel="stylesheet">
  <link href="lib/css/navbar.css" rel="stylesheet">
  <link href="lib/css/housing.css" rel="stylesheet">
  <script src="lib/js/jquery-1.11.2.min.js"> </script>
  <script src="lib/js/bootstrap.js"> </script>
  
</head>

<body>
<%@ page import="java.sql.ResultSet, model.Preference" %>
<%@ include file="navbar.jsp" %>

<img src="/Housing/lib/images/houSCing.png" width="300px" height="150px" class="img-responsive heading" alt="Housing Logo"/>

<form class="search-form" method="post" action="/Housing/search">
	<input class="stretch" type="text" placeholder="Search for Users or Housing" name="keyword" required/>
	<div>
		<span>Search by: </span>
		
		<select name="search-type">
	    	<option value="user" selected="selected">User</option>
	    	<option value="housing">Housing</option> 	
		</select>
		<input type="submit" value="Search" class="search-button"/>
	</div>
</form>

<!-- For each return from the query create an html div displaying all information -->
<% 
ResultSet result = (ResultSet)request.getAttribute("queried-result");
String type = (String)request.getAttribute("search-type");
while(result != null && result.next()){
	if(type != null && type.equals("user")){
		ResultSet prefs = (ResultSet)request.getAttribute("user-prefs");
		prefs.next();
%>		
		
	<div class="info-box">
		
		<table class="info-table">
	  		<tr>
			    <td>
					<h3><%= result.getString("firstName") %> <%= result.getString("lastName") %></h3>
					<hr/>
					Grade Level: <%= prefs.getString("schoolYear") %>
					<br/>
					Preferred Room Type: <%= prefs.getString("preferredRoomType") %>
					<br/>
					Hobbies/Interests: <%= prefs.getString("hobbies") %>
					<br/>
					Fun Fact: <%= prefs.getString("funFact") %>	
				</td>
			    <td>
					Cook Frequency: <%= prefs.getInt("cookFrequency") %>/10
					<br/>
					Cleanliness <%= prefs.getInt("cleanliness") %>/10
					<br/>
					Party Frequency: <%= prefs.getInt("partyFrequency") %>/10
					<br/>
					Guest Frequency: <%= prefs.getInt("guestFrequency") %>/10
					<br/>
					Preferred Music Level: <%= prefs.getInt("musicLoudness") %>/10
					<br/>
					Studies in Room: <%= prefs.getInt("studyFrequency") %>/10
					<br/>
					Level of Privacy: <%= prefs.getInt("privacy") %>/10
					<br/>
					Typical Sleeping Time: <%= prefs.getTime("sleepingTime") %>
					<br/>
					Typical Waking Time: <%= prefs.getTime("wakingTime") %>
					<br/>
					Pets: <%if(prefs.getBoolean("pets") == true){ %>Yes<%}else{%>No<%} %>
					<br/>
					Smokes: <%if(prefs.getBoolean("smokes") == true){ %>Yes<%}else{%>No<%} %>
					<br/>
					Greek Life: <%if(prefs.getBoolean("greek") == true){ %>Yes<%}else{%>No<%} %>	
				</td>
	  		</tr>
		</table>
	</div>
		
		
<%		
	} else if (type != null && type.equals("housing")){
%>		
		
	<div class="info-box">
	
		<div class="info-table">

		    	<% String address= result.getString("address");
		    	address=address.replace(' ', '_');
		    	%>
		    	<% String img= "lib/images/" +address + ".jpg";
		    	
		    	%>
				<img src=<%= img %> class="searchPic">
				
				<div class="info-table-address">
						<%= result.getString("address") %>

					<ul>
						<li> Distance: <%= result.getDouble("distance") %> miles</li>
						<li> Rent: $<%= result.getInt("rent") %>/month</li>
						<li> Minutes from Tommy: <%= result.getInt("minutes") %></li>
						<li> Number of Beds: <%= result.getInt("bed") %></li>
						<li> Number of Baths: <%= result.getInt("bath") %></li>
						<li> USC Owned: <%if(result.getInt("uscOwned") == 1){ %>YES<%}else{%>NO<%} %> </li>
						<li> Wifi: <%if(result.getInt("wifi") == 1){ %>YES<%}else{%>NO<%} %> </li>
						<li> A/C: <%if(result.getInt("ac") == 1){ %>YES<%}else{%>NO<%} %></li>
						<li> Parking: <%if(result.getInt("parking") == 1){ %>YES<%}else{%>NO<%} %> </li>
						<li> Cable: <%if(result.getInt("cable") == 1){ %>YES<%}else{%>NO<%} %> </li>
					</ul>
				</div>
		</div>
		
		<script>
		var images= document.getElementsByClassName("searchPic");
	
		for(var aa=0; aa<images.length; aa++)
		{	
			var image= images[aa];
			image.onerror= function(){
				this.src= "lib/images/genericHouse.jpg";
			}
		}
		
		</script>
		
				

		<div <% if(session.getAttribute("curr") == null){ %> class="parent-div invisible" <%} %> >
		
		<form id="save-form" class="inline-form" action="/Housing/save-housing" method="post">
		<input type="text" class="invisible" name="housingID" value=<%= "" + result.getInt("keyid") %> />
		<input type="submit" class="form-button" value="Save"/>
		</form>
		
		<form id="review-form" class="inline-form" action="/Housing/review-housing" method="post">
		<input type="text" class="invisible" name="housingID" value=<%= "" + result.getInt("keyid") %> />
		<input type="submit" class="form-button" value="Review"/>
		</form>
		
		</div>
	</div>
		
<%		
	}
}
%>

<!-- ---------------------------------------------------------------------------- -->

</body>
</html>
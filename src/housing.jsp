<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8">

  <title>Housing</title>

  <link href="lib/css/bootstrap.css" rel="stylesheet">
  <link href="lib/css/navbar.css" rel="stylesheet">
  <link href="lib/css/housing.css" rel="stylesheet">
  <script src="lib/js/jquery-1.11.2.min.js"> </script>
  <script src="lib/js/bootstrap.js"> </script>

</head>

<body>
<%@ page import="java.sql.ResultSet, java.text.SimpleDateFormat" %>
<%@ include file="navbar.jsp" %>
<img src="/Housing/lib/images/houSCing.png" width="300px" height="150px" class="img-responsive heading" alt="Housing Logo"/>

<% 
ResultSet result = (ResultSet)request.getAttribute("housingInfo");
result.next();
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
		
		<!-- Only enable this if the user hasn't already saved the building  -->
		<div>	
		<form id="save-form" class="inline-form" action="/Housing/save-housing" method="post">
		<input type="text" class="invisible" name="housingID" value=<%= "" + result.getInt("keyid") %> />
		<input type="submit" class="form-button" value="Save"/>
		</form>	
		</div>
	</div>
	
	<div class="info-box">
	<form method="post" action="/Housing/add-review">
	<h2>Enter your review</h2>
	<input type="text" class="invisible" name="housingID" value=<%= "" + result.getInt("keyid") %> />
	<textarea class="form-control" rows="5" name="review-content" id="comment"></textarea>
	<select name="review-stars">
		<option value="1" selected="selected">1 Star</option>
		<option value="2">2 Star</option>
		<option value="3">3 Star</option>
		<option value="4">4 Star</option>
		<option value="5">5 Star</option>
	</select>
	<input type="submit" class="form-button" value="Review"/>
	</form>
	</div>
	
	<%
	ResultSet reviews = (ResultSet)request.getAttribute("housingReviews");
	while(reviews.next()){
	%>
	
	<%
	SimpleDateFormat ft = new SimpleDateFormat ("MMMM dd, y");
	%>
	
	<div class="info-box" style="text-align:left;">
	<span style="float:right;"><%= ft.format(reviews.getDate("reviewDate")) %></span>
	<h4><%= reviews.getString("author") %></h4>
	<%
	int filledStars = 0;
	for(int i = 0; i < 5; i++){ 
		if(filledStars < reviews.getInt("stars")){
	%>
		<i class="glyphicon glyphicon-star"></i>
	<%	filledStars++;
		} else { 
	%>
		<i class="glyphicon glyphicon-star-empty"></i>
	<%	} %>
	
	<% } %>
	<br/>
	<%= reviews.getString("content") %>
	
	</div>
	<% } %>

</body>
</html>
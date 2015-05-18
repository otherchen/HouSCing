<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8">

  <title>Top Housing</title>

  <link href="lib/css/bootstrap.css" rel="stylesheet">
  <link href="lib/css/navbar.css" rel="stylesheet">
  <link href="lib/css/housing.css" rel="stylesheet">
  <script src="lib/js/jquery-1.11.2.min.js"> </script>
  <script src="lib/js/bootstrap.js"> </script>

</head>

<body>
<%@ page import="java.sql.ResultSet" %>
<%@ include file="navbar.jsp" %>
<img src="/Housing/lib/images/houSCing.png" width="300px" height="150px" class="img-responsive heading" alt="Housing Logo"/>
<%
 ResultSet result = (ResultSet)request.getAttribute("top-housing");
%>

<%
 if(result == null || !result.isBeforeFirst()){
%>	
	<div style="width:100%; text-align:center;"><h1>You have not saved any housing!</h1></div> 
<%	 
 } else {
	 while(result.next()){
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
		
		<form id="remove-form" class="inline-form" action="/Housing/remove-housing" method="post">
		<input type="text" class="invisible" name="housingID" value=<%= "" + result.getInt("keyid") %> />
		<input type="submit" class="form-button" value="Remove"/>
		</form>
		
		</div>
	</div>

<%   }
 }  %>
</body>
</html>
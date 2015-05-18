<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8">

  <title>Roommates</title>

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
	ResultSet result = (ResultSet)request.getAttribute("user-roommates");
%>

<%
	if(!result.isBeforeFirst()){
%>
	<div style="width:100%; text-align:center;"><h1>You have no roommates!</h1></div>
<%
	} else {
		while(result.next()){
%>
		<div class="info-box">
		
		<table class="info-table">
	  		<tr>
			    <td>
					<h3><%= result.getString("firstName") %> <%= result.getString("lastName") %></h3>
					<hr/>
				</td>
	  		</tr>
		</table>
		<div <% if(session.getAttribute("curr") == null){ %> class="invisible" <%} %> >
		
		<form id="request-form" class="inline-form" action="/Housing/user-request" method="post">
		<input type="text" class="invisible" name="userID" value=<%= "" + result.getInt("userID") %> />
		<input type="submit" class="form-button" value="Remove"/>
		</form>
		
		<form id="chat-form" class="inline-form" action="/Housing/user-chat" method="post">
		<input type="text" class="invisible" name="housingID" value=<%= "" + result.getInt("userID") %> />
		<input type="submit" class="form-button" value="Chat"/>
		</form>
		</div>
	</div>	

<%  	}
	} %>

</body>
</html>
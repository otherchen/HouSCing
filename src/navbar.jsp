<!-- Make any changes to the nav bar here! -->
<%@ page import="model.User" %>

<!-- Creating the Nav Bar -->
  	<div class= "navbar navbar-fixed-top navbar-inverse">
	  	<div class="container">
	  	
	  	<a href="#" class="pull-left navbar-brand"><img width="90px" height="22px" src="/Housing/lib/images/houSCingWhite.png"></a>  	
	  		<ul class="nav navbar-nav">
	  			<li id="search">
		        <a href="/Housing/search"> 
		          <i class="glyphicon glyphicon-search">
		          </i> Search
		        </a>
		      </li>
	  		</ul>
	  		<ul class="nav navbar-nav">
	  			<li id="home">
		        <a href="/Housing/src/mapPage.jsp"> 
		          <i class="glyphicon glyphicon-map-marker">
		          </i> Map
		        </a>
		      </li>
	  		</ul>
	  		
	
	  		
	  		
	  		
	  		

		<% if(session.getAttribute("curr") != null){%>
		
			<ul class="nav navbar-nav">
	  			<li id="chatButtonOnNavbar">
		          <a href="/Housing/src/chat.jsp"> 
		          <i class="glyphicon glyphicon-flag"> </i> Chat
		          </a>
		      </li>
	  		</ul>
	  		
	  		<ul class="nav navbar-nav">
	  			<li id="chatButtonOnNavbar">
		          <a href="/Housing/src/friends.jsp"> 
		          <i class="glyphicon glyphicon-heart"> </i> Friends
		          </a>
		      </li>
	  		</ul>
	  		
			<ul class="nav navbar-nav pull-right">
			<p class="navbar-text">Welcome, <%= ((User)(session.getAttribute("curr"))).getFirstName() %></p>
				<li class="dropdown">
          			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
          			<i class="glyphicon glyphicon-user"></i> <span class="caret"></span></a>
          			<ul class="dropdown-menu" role="menu">
            			<li>
		        			<a href="/Housing/profile"> 
		          			<i class="glyphicon glyphicon-user">
		          			</i> My Profile
		       			 	</a>
		      			</li>
		      			<li>
		        			<a href="/Housing/top-housing"> 
		          			<i class="glyphicon glyphicon-map-marker">
		          			</i> Top Housing
		       			 	</a>
		      			</li>
			            <li class="divider"></li>
			            <li>
			            	<a href="/Housing/logout">
			            	<i class="glyphicon glyphicon-off">
		          			</i> Logout
			            	</a>
			            </li>
          			</ul>   
          		</li>   			
          	</ul>
		  
		 <%} else {%>
		 
		 	<ul class="nav navbar-nav pull-right">
				<li class="dropdown">
          			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
          			<i class="glyphicon glyphicon-user"></i> <span class="caret"></span></a>
          			<ul class="dropdown-menu" role="menu">
            			<li>
		        			<a href="/Housing/login"> 
		          			<i class="glyphicon glyphicon-user">
		          			</i> Login
		        			</a>
		      			</li>
			            <li class="divider"></li>
			            <li>
		        			<a href="/Housing/signup"> 
		          			<i class="glyphicon glyphicon-circle-arrow-up">
		          			</i> Sign Up
		        			</a>
		      			</li>
          			</ul>   
          		</li>   			
          	</ul>
          
         <%} %>   
		 
	  	</div>
	</div>
<!-- Finished creating the Nav Bar -->

<% 
//setting the type for the alert box
String alertType = null;
String alertPhrase = null;
if(request.getAttribute("success") != null && (Boolean)request.getAttribute("success") == true){
	alertType = "alert-success";
	alertPhrase = "Success!";
} else if (request.getAttribute("success") != null && (Boolean)request.getAttribute("success") == false) {
	alertType = "alert-danger";
	alertPhrase = "Error!";
}
%>

<% if(alertType != null){ %>
<div class="alert <%= alertType %> alert-dismissable fade in">
    <span class="close" data-dismiss="alert">&times;</span>
    <strong><%= alertPhrase %></strong> <%= request.getAttribute("message") %>
</div>
<% request.setAttribute("success", null); %>
<% request.setAttribute("message", null); %>
<% } %>
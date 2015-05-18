<!DOCTYPE html>
<!-- http://127.0.0.1:8080/Housing/src/mapPage.jsp -->

<html>
<head>
	<meta charset= "utf-8"/>
	<title> USC Housing Map </title>
	<link href="../lib/css/bootstrap.css" rel="stylesheet">
	  <link href="../lib/css/navbar.css" rel="stylesheet">
	<link href="../lib/css/mapPage.css" rel= "stylesheet">
	<script src="../lib/js/jquery-1.11.2.min.js"> </script>
	<script src="../lib/js/bootstrap.js"> </script>
	
	<script src="https://maps.googleapis.com/maps/api/js"></script>

</head>
<body>

<div id="overallContent">

<%@ include file="navbar.jsp" %>
<%@page import= "java.sql.DriverManager" %>
<%@page import= "java.sql.PreparedStatement" %>
<%@page import= "java.sql.ResultSet" %>
<%@page import= "java.sql.SQLException" %>
<%@page import= "java.sql.Connection" %>
<%@page import="org.json.JSONArray" %>
<%@page import ="org.json.JSONObject" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.io.File" %>
<%


	
	ArrayList<String>  houseAddressArray= new ArrayList();
	ArrayList<Integer> houseBedArray= new ArrayList();
	ArrayList<Integer> houseBathArray= new ArrayList();
	ArrayList<Integer> houseRentArray= new ArrayList();
	ArrayList<Integer> houseWifiArray= new ArrayList();
	ArrayList<Integer> houseAcArray= new ArrayList();
	ArrayList<Integer> houseParkingArray= new ArrayList();
	ArrayList<Integer> houseCableArray= new ArrayList();
	ArrayList<Double>  houseLatitudeArray= new ArrayList();
	ArrayList<Double>  houseLongitudeArray= new ArrayList();
	ArrayList<Double>  houseDistanceArray= new ArrayList();
	ArrayList<Integer> houseMinutesArray= new ArrayList();
	ArrayList<Integer> houseUscOwnedArray= new ArrayList();
	ArrayList<Integer> housevisibilityCounterArray= new ArrayList();
	ArrayList<String>  houseHouseOrApartmentArray= new ArrayList();
	//ArrayList<Integer> houseSameFilterCheckArray= new ArrayList();
	

	JSONArray ja= new JSONArray();
	try{
	
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/csci201_database?user=root&password=212340Cba");   
	
		 PreparedStatement ps= conn.prepareStatement("SELECT * FROM housing_table");
		 ResultSet rs= ps.executeQuery();
		 

		 
		while(rs.next())
		{
			 
			 String address= rs.getString("address");
			 Integer bed= rs.getInt("bed");
			 Integer bath= rs.getInt("bath");
			 Integer rent= rs.getInt("rent");
			 Integer wifi= rs.getInt("wifi");
			 Integer ac = rs.getInt("ac");
			 Integer parking=rs.getInt("parking");
			 Integer cable= rs.getInt("cable");
			 Double latitude=rs.getDouble("latitude");
			 Double longitude= rs.getDouble("longitude");
			 Double distance= rs.getDouble("distance");
			 Integer minutes= rs.getInt("minutes");
			 Integer uscOwned= rs.getInt("uscOwned");
			 Integer visibilityCounter= rs.getInt("visibilityCounter");
			 String houseOrApartment= rs.getString("houseOrApartment");
			
			 
			 
			 //to account for last row that is null
			 if(address==null)
			 {
				 break;
			 }
			 
			 houseAddressArray.add(address);
			 houseBedArray.add(bed);
			 houseBathArray.add(bath);
			 houseRentArray.add(rent);
			 houseWifiArray.add(wifi);
			 houseAcArray.add(ac);
			 houseParkingArray.add(parking);
			 houseCableArray.add(cable);
			 houseLatitudeArray.add(latitude);
			 houseLongitudeArray.add(longitude);
			 houseDistanceArray.add(distance);
			 houseMinutesArray.add(minutes);
			 houseUscOwnedArray.add(uscOwned);
			 housevisibilityCounterArray.add(visibilityCounter);
			 houseHouseOrApartmentArray.add(houseOrApartment);

		}
		
		


		 
		 rs.close();
		 conn.close();
		 
		
		
	}
	catch(SQLException sqle)
	{
		out.println("SQLE: " +sqle.getMessage());
	}
	catch(ClassNotFoundException cnfe)
	{
		out.println("CNFE: " + cnfe.getMessage());
	}
	
	    
	   
	    
	    for(int ii=0; ii<houseAddressArray.size(); ii++)
	    {
	    	JSONObject jo= new JSONObject();
	    	jo.put("address", houseAddressArray.get(ii));
	    	jo.put("bed", houseBedArray.get(ii));
	    	jo.put("bath", houseBathArray.get(ii));
	    	jo.put("rent", houseRentArray.get(ii));
	    	jo.put("wifi", houseWifiArray.get(ii));
	    	jo.put("ac", houseAcArray.get(ii));
	    	jo.put("parking", houseParkingArray.get(ii));
	    	jo.put("cable", houseCableArray.get(ii));
	    	jo.put("latitude", houseLatitudeArray.get(ii));
	    	jo.put("longitude", houseLongitudeArray.get(ii));
	    	jo.put("distance", houseDistanceArray.get(ii));
	    	jo.put("minutes", houseMinutesArray.get(ii));
	    	jo.put("uscOwned", houseUscOwnedArray.get(ii));
	    	jo.put("visibilityCounter", housevisibilityCounterArray.get(ii));
	    	jo.put("houseOrApartment", houseHouseOrApartmentArray.get(ii));

	    	ja.put(jo);
	    }
	    

%>


	<!-- initialize map after getting all of the data from the database.  first set the variables so the
	javascript file can actually find them -->
	<script>
	
	
	
	
	var myArray= <%= ja %>
	var arrayAsString= JSON.stringify(myArray);
	//parse takes in a string, so need to convert the array into a string using JSON.stringfy()
	var listObj= JSON.parse(arrayAsString);
	
	//make this element visible to both scripts.
	var map;
	var markers= [];

	</script>


<div class="container-fluid" id="mainBody">
	<div id="filter-bar">
		<div class="filterRow1">
			<label for="numBeds"> Beds</label>
			<select id="numBeds">
				<option value="-1">Any </option>
				<option value="1"> 1</option>
				<option value="2"> 2</option>
				<option value ="3">3 </option>
				<option value ="4">4 </option>
				<option value= "5"> 5+</option>
			</select>
			
			<label for="numBaths">&emsp; Baths:</label>
			<select id="numBaths">
				<option value="-1">Any</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5+</option>
			
			</select>
			
			<label for="priceRange">&emsp; Max Price:</label>
			<select id="priceRange">
				<option value= "-1"> Any </option>
				<%for(int ii=500; ii<=2500; ii+=50) 
				 {
				%>
					<option value= "<%=ii%>"> $<%=ii%> </option>
				<%
				 }
				%>
				
			</select>
			
			<label for="distanceMiles">&emsp; Max Distance: </label>
			<select id="distanceMiles">
				<option value="-1"> Any Miles</option>
				<option value =".1"> .1 miles </option>
				<option value =".2"> .2 miles </option>
				<option value =".3"> .3 miles </option>
				<option value =".4"> .4 miles </option>
				<option value =".5"> .5 miles </option>
				<option value =".6"> .6 miles </option>
				<option value =".7"> .7 miles </option>
				<option value =".8"> .8 miles </option>
				<option value =".9"> .9 miles </option>
				<option value ="1.0"> 1.0 miles </option>
				<option value ="1.1"> 1.1 miles </option>
				<option value ="1.2"> 1.2 miles </option>
				<option value ="1.3"> 1.3 miles </option>
				<option value ="1.4"> 1.4 miles </option>
				<option value ="1.5"> 1.5 miles </option>
	
			</select>
			
			<label for="distanceMinutes"> &emsp; </label>
			<select id="distanceMinutes">
				<option value="-1"> Any Minutes</option>
				<% for(int ii=5; ii<=25; ii++)
				  {
				%>
						<option value="<%=ii%>"> <%=ii%> minutes </option>
				<%
				  }
				%>
			</select>
		</div>
		<div class="filterRow2">
			
			
			<label for="wifiCheckbox"> Wifi:</label>
			<input type="checkbox" id="wifiCheckbox" >
			
			<label for="acCheckbox" class="row2Item"> Air Conditioning:</label>
			<input type="checkbox" id="acCheckbox" >
			
			<label for="parkingCheckbox"  class="row2Item"> Parking:</label>
			<input type="checkbox" id="parkingCheckbox">
			
			<label for="cableCheckbox" class="row2Item"> Cable:</label>
			<input type="checkbox" id="cableCheckbox" >
			
			<label for="uscOwnedCheckbox" class="row2Item"> USC Owned:</label>
			<input type="checkbox" id="uscOwnedCheckbox">

		
		</div>
		
		
	</div>
	<div id="uscLogo">

		<img src="../lib/images/usc.jpg" alt="usc logo" id="uscLogoImg"> 
	</div>
	

	<div id="map-canvas"> 
	</div>
	
	<div id="pictureMenu" style="cursor: pointer;"> 
		<% for(int ii=0; ii<ja.length(); ii++)
			{
					JSONObject jsonObj= ja.getJSONObject(ii);
					String address= jsonObj.getString("address");
					int bed= jsonObj.getInt("bed");
					int bath= jsonObj.getInt("bath");
					int rent= jsonObj.getInt("rent");
					int wifi=jsonObj.getInt("wifi");
					int ac= jsonObj.getInt("ac");
					int parking=jsonObj.getInt("parking");
					int cable= jsonObj.getInt("cable");
					double distance= jsonObj.getDouble("distance");
					int minutes= jsonObj.getInt("minutes");
					int uscOwned= jsonObj.getInt("uscOwned");
					
					//converting the spaces in the addres to underscores for the img url
					String img=address.replace(' ', '_');
					
		
							
					String yesOrNo_wifi="";
					if(wifi==1)
					{
						yesOrNo_wifi="YES";
					}
					else
					{
						yesOrNo_wifi="NO";
					}			
					
					String yesOrNo_ac="";
					if(ac==1)
					{
						yesOrNo_ac="YES";
					}
					else
					{
						yesOrNo_ac="NO";
					}
					
					String yesOrNo_parking="";
					if(parking==1)
					{
						yesOrNo_parking="YES";
					}
					else
					{
						yesOrNo_parking="NO";
					}
					
					String yesOrNo_cable="";
					if(cable==1)
					{
						yesOrNo_cable="YES";
					}
					else
					{
						yesOrNo_cable="NO";
					}
							

					String yesOrNo_uscOwned="";
					if(uscOwned==1)
					{
						yesOrNo_uscOwned="YES";
					}
					else
					{
						yesOrNo_uscOwned="NO";
					}
		%>
				<div id= "<%=address %>" class="eachHouse">
					<div class= "titleForEachHouse">
						<%= address %>
					
					</div>
				
				
					<div class= "houseAttributes">
						
						<span class="attributesLabel"> Bed/Bath: </span><%=bed %>  bd/<%=bath%>ba  &emsp;&emsp;&emsp; 
						<%
						//account for formating issues of extra space
						if(bed<10)
						{ 
						%>
							&nbsp;
						<% 	
						}
						%> 
						 <span class="attributesLabel"> Rent: </span>$<%= rent %> <br>
						<span class="attributesLabel">Distance: </span> <%= distance %> miles &emsp; &emsp; &nbsp;&nbsp; &nbsp;  <span class="attributesLabel">USC Owned: </span><%= yesOrNo_uscOwned%>
						<img src="../lib/images/<%=img%>.jpg" class="houseIcon"> 
					</div>
				</div>
				
				<div id="<%=address %>_fullInfo" style="display: none;" class= houseFullInfo>
				
					<div class="titleForEachHouse_fullInfo">
						 <%=address %>	
					</div>
					
					<ul>
						<li>Number of Beds: <%=bed %> </li>
						<li>Number of Baths: <%=bath %> </li>
						<li>Rent: $<%=rent %> </li>
						<li>Distance (miles): <%=distance %> mi </li>
						<li>Minutes from Tommy: <%=minutes %> min</li>	
						<li>Wifi: <%=yesOrNo_wifi %> </li>	
						<li>Air Conditioning: <%=yesOrNo_ac %> </li>
						<li>Parking: <%=yesOrNo_parking %> </li>
						<li>Cable: <%=yesOrNo_cable %> </li>
						<li>USC Owned: <%=yesOrNo_uscOwned %> </li>
						
					</ul>
					
				</div>
		<% 
			}
		%>	

		
	</div>
	
	<script>
		var images= document.getElementsByClassName("houseIcon");
	
		for(var aa=0; aa<images.length; aa++)
		{	
			var image= images[aa];
			image.onerror= function(){
				this.src= "../lib/images/genericHouse.jpg";
			}
		}
		
	</script>
	
	<script src="../lib/js/mapPage_googleMaps.js"> </script>  
	<script src= "../lib/js/mapPage_filters.js"> </script>
	<script src= "../lib/js/mapPage_pictureMenu.js"></script>


</div>


</div>
</body>
</html>
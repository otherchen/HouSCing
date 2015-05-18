function initialize()
{

	var mapCanvas= document.getElementById('map-canvas');
	 var mapOptions = {
          center: new google.maps.LatLng(34.026436, -118.283619),
          zoom: 15,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        }

	map= new google.maps.Map(mapCanvas, mapOptions);


	
	for(var ii=0; ii<listObj.length; ii++)
	{
		var image="";
		if(listObj[ii].houseOrApartment == "house")
		{
			image="../lib/images/home_icon_small.png";
		}
		else
		{
			image="../lib/images/apartment_icon_small.png";
		}

		var myLatLng= new google.maps.LatLng(listObj[ii].latitude,listObj[ii].longitude);
		var marker= new google.maps.Marker({
		position: myLatLng,
		map:map,
		title: listObj[ii].address,
		icon: image
		});
		
		google.maps.event.addListener(marker, 'click', (function(marker, ii) {
			return function() {
				locateHouseOnSideBar(marker.title);
			}
		})(marker, ii));

		markers.push(marker);
	}

	
}



google.maps.event.addDomListener(window,'load', initialize);


function locateHouseOnSideBar(markerTitle)
{

//set all the background colors back to white before highlighting the next house or else
// you will just keep on highlighting houese
	var allHouses= document.getElementsByClassName("eachHouse");
	for(var ii=0; ii<allHouses.length; ii++)
	{
		
		var house= allHouses[ii];
		//reset the background to red
		house.style.background= '#FFFFF1';
		var houseLabels= house.querySelectorAll(".attributesLabel");
		
		//reset the span labels to teal
		for(var jj=0; jj<houseLabels.length; jj++)
		{
			var houseLabel= houseLabels[jj];
			houseLabel.style.color='#B00000';
		}


	}

	//the id of each house on the right of the map is the address of the house
	//which is the same as the markerTitle, so set the background of the specific house on the side bar
	//to teal and the labels to black
	var clickedHouse= document.getElementById(markerTitle);
	clickedHouse.style.background ='#80B2FF';
	


	//Without this, when you scrolled to a div, it changed all of the relative positions of the divs.  For example
	//when you scrolled to an element at position 870, it correctly scrolled that element to the top.  However, that div now had a 
	//relative position of 0, so when you clicked the same element again, it scrolled the menu to 0, it scrolled
	//to position 0 even though the actual position was 870.  So scrolling to 0 first when the user clicks
	//on the menu allows for the div elements to retain their relative positions to 0 which means the menu
	//will scroll to the absolute position of each div.
	$("#pictureMenu").scrollTop(0);

	var clickedHousePosition= $(clickedHouse).position().top;


	$("#pictureMenu").animate({scrollTop: clickedHousePosition});

}


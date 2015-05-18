

$(document).ready(function(){

	var BedFilterArray = [];
	$("#numBeds").change(function()
	{

		//this is to ensure that you don't decrement the visibilityCounter by more than 1 per filter
		//houses in this array means that the bed filter has decremented their visibilityCounter, and so when changing
		//the bed filter after the first time, you want to add 1 back to their visibilityCounter and then do the processing again.  You
		//only want to add 1 to the visibilityCounter of the houses that had their visibilityCounters
		//decremented by the bed filter. You don't want to add 1 to every houses' visibilityCounter because some
		//of the visibility Counters might have been changed by other filters.
		for(var ii=0; ii<BedFilterArray.length; ii++)
		{
			var myListObj= BedFilterArray[ii];
			myListObj.visibilityCounter++;
		}

		//empty the array 
		BedFilterArray = [];

		for(var ii=0; ii<listObj.length; ii++)
		{
			if(listObj[ii].visibilityCounter ==1)
			{
				markers[ii].setMap(map);
			}
		}
	
		
		var numberOfBeds= $(this).val();

		//-1 signifies that any number of beds are ok
		if(numberOfBeds != -1)
		{
			//This is separate because houses with 5+ beds are viable.  
			if(numberOfBeds ==5 )
			{
				for(var ii=0; ii<listObj.length; ii++)
				{
					if(listObj[ii].bed <5)
					{
						listObj[ii].visibilityCounter--;
						markers[ii].setMap(null);
						//identifying which houses had their visibilityCounter changed so when you change the bed filter
						//it adds one back to these houses' visibilityCounters so their visibilityCounter is not decremented
						//by more than one for this bed filter.  Basically, for all houses that had visibiliytCounter decremented
						//by the bed filter, you want to reset their visibilityCounter back to whatever it was before the bed filter
						//decremented their visibilityCounter and then do the processing again and change the visibilitCounter of the
						//houses for the new bed requirement. This way you never reduce the
						// visibilityCounter by more than 1 per filter
						BedFilterArray.push(listObj[ii]);
						
					}
				}	
			}
			else
			{
				for(var ii=0; ii<listObj.length; ii++)
				{
					if(listObj[ii].bed != numberOfBeds)
					{
					
						listObj[ii].visibilityCounter--;
						markers[ii].setMap(null);

						BedFilterArray.push(listObj[ii]);
						
					}
				}
			}
		}
		
	});



	var BathFilterArray = [];
	$("#numBaths").change(function()
	{

		for(var ii=0; ii<BathFilterArray.length; ii++)
		{
			var myListObj= BathFilterArray[ii];
			myListObj.visibilityCounter++;
		}

		//empty the array 
		BathFilterArray = [];

		for(var ii=0; ii<listObj.length; ii++)
		{
			if(listObj[ii].visibilityCounter ==1)
			{
				markers[ii].setMap(map);
			}
		

		}
	
		
		var numberOfBaths= $(this).val();

		
		if(numberOfBaths != -1)
		{
			
			if(numberOfBaths ==5 )
			{
				for(var ii=0; ii<listObj.length; ii++)
				{
					if(listObj[ii].bath <5)
					{
						listObj[ii].visibilityCounter--;
						markers[ii].setMap(null);
						BathFilterArray.push(listObj[ii]);
						
					}
				}	
			}
			else
			{
				for(var ii=0; ii<listObj.length; ii++)
				{
					if(listObj[ii].bath != numberOfBaths)
					{
					
						listObj[ii].visibilityCounter--;
						markers[ii].setMap(null);
						BathFilterArray.push(listObj[ii]);
						
					}
				}
			}
		}
		
	});


	var PriceFilterArray = [];
	$("#priceRange").change(function()
	{
	

		for(var ii=0; ii<PriceFilterArray.length; ii++)
		{
			var myListObj= PriceFilterArray[ii];
			myListObj.visibilityCounter++;
		}

		//empty the array 
		PriceFilterArray = [];

		for(var ii=0; ii<listObj.length; ii++)
		{
			if(listObj[ii].visibilityCounter ==1)
			{
				markers[ii].setMap(map);
			}
		

		}
	
		
		var maxPrice= $(this).val();

		
		if(maxPrice != -1)
		{
			for(var ii=0; ii<listObj.length; ii++)
			{
				if(listObj[ii].rent > maxPrice)
				{
				

					listObj[ii].visibilityCounter--;
					markers[ii].setMap(null);
					PriceFilterArray.push(listObj[ii]);
					
				}
			}
			
		}
		
	});


	var MilesFilterArray = [];
	$("#distanceMiles").change(function()
	{
	

		for(var ii=0; ii<MilesFilterArray.length; ii++)
		{
			var myListObj= MilesFilterArray[ii];
			myListObj.visibilityCounter++;
		}

		//empty the array 
		MilesFilterArray = [];

		for(var ii=0; ii<listObj.length; ii++)
		{
			if(listObj[ii].visibilityCounter ==1)
			{
				markers[ii].setMap(map);
			}
		

		}
	
		
		var maxMiles= $(this).val();

		
		if(maxMiles != -1)
		{
			for(var ii=0; ii<listObj.length; ii++)
			{
				if(listObj[ii].distance > maxMiles)
				{
				

					listObj[ii].visibilityCounter--;
					markers[ii].setMap(null);
					MilesFilterArray.push(listObj[ii]);
					
				}
			}
			
		}
		
	});



	var MinutesFilterArray = [];
	$("#distanceMinutes").change(function()
	{
	

		for(var ii=0; ii<MinutesFilterArray.length; ii++)
		{
			var myListObj= MinutesFilterArray[ii];
			myListObj.visibilityCounter++;
		}

		//empty the array 
		MinutesFilterArray = [];

		for(var ii=0; ii<listObj.length; ii++)
		{
			if(listObj[ii].visibilityCounter ==1)
			{
				markers[ii].setMap(map);
			}
		

		}
	
		
		var maxMinutes= $(this).val();

		
		if(maxMinutes != -1)
		{
			for(var ii=0; ii<listObj.length; ii++)
			{
				if(listObj[ii].minutes > maxMinutes)
				{
				

					listObj[ii].visibilityCounter--;
					markers[ii].setMap(null);
					MinutesFilterArray.push(listObj[ii]);
					
				}
			}
			
		}
		
	});




	//initially th checkbox is unchecked, and this method only gets invoked when a change occurs
	//so the first change will occur when the wifiCheckbox is Checked
	var wifiCheckboxChecked=false;
	$("#wifiCheckbox").click(function(){
		
		//if wifiCheckboxChecked is false, then once you click on it it becomes true
		//or if wifiCheckBoxedChecked is true, then once you click to uncheck it, it becomes false
		wifiCheckboxChecked= !wifiCheckboxChecked;

		if(wifiCheckboxChecked)
		{
			for(var ii=0; ii<listObj.length; ii++)
			{
				if(listObj[ii].wifi ==0)
				{
					listObj[ii].visibilityCounter--;
					markers[ii].setMap(null);
					
				}
			}
		}
		else //re add the markers that the wifiCheckbox took out because checkbox is now unchecked
		{
			for(var ii=0; ii<listObj.length; ii++)
			{
				if(listObj[ii].wifi ==0 )
				{
					//When you checked the wifi filter, it subtracted 1 from
					//the visibility counter of the houses that did not have wifi  Now
					//that you deselect the petsAllowed filter, you should add 1 back to the
					//houses' visibility counters that you had subtracted 1 from for this particular filter.
					//You don't want to add 1 to all of the houses' visibility counters because
					//some houses' visibility counters were not affected when this filter was selected.
					listObj[ii].visibilityCounter++;
					if(listObj[ii].visibilityCounter ==1 )
					{
							markers[ii].setMap(map);
					}
				
					
				}
			}
		}
	});




	var acCheckboxChecked=false;
	$("#acCheckbox").click(function(){
		

		acCheckboxChecked= !acCheckboxChecked;

		if(acCheckboxChecked)
		{
			for(var ii=0; ii<listObj.length; ii++)
			{
				if(listObj[ii].ac ==0)
				{
					listObj[ii].visibilityCounter--;
					markers[ii].setMap(null);
					
				}
			}
		}
		else 
		//acCheckbox is now unchecked so add 1 back to the visibility counters of all the houses that you 
		//had subtracted 1 from because they didn't have air conditioning.
		{
			for(var ii=0; ii<listObj.length; ii++)
			{
				if(listObj[ii].ac ==0 )
				{
			
					listObj[ii].visibilityCounter++;
					if(listObj[ii].visibilityCounter ==1 )
					{
							markers[ii].setMap(map);
					}
				
					
				}
			}
		}
	});


	var parkingCheckboxChecked=false;
	$("#parkingCheckbox").click(function(){
		

		parkingCheckboxChecked= !parkingCheckboxChecked;

		if(parkingCheckboxChecked)
		{
			for(var ii=0; ii<listObj.length; ii++)
			{
				if(listObj[ii].parking ==0)
				{
					listObj[ii].visibilityCounter--;
					markers[ii].setMap(null);
					
				}
			}
		}
		else 
		{
			for(var ii=0; ii<listObj.length; ii++)
			{
				if(listObj[ii].parking ==0 )
				{
			
					listObj[ii].visibilityCounter++;
					if(listObj[ii].visibilityCounter ==1 )
					{
						markers[ii].setMap(map);
					}
				
					
				}
			}
		}
	});


	var cableCheckboxChecked=false;
	$("#cableCheckbox").click(function(){
		

		cableCheckboxChecked= !cableCheckboxChecked;

		if(cableCheckboxChecked)
		{
			for(var ii=0; ii<listObj.length; ii++)
			{
				if(listObj[ii].cable ==0)
				{
					listObj[ii].visibilityCounter--;
					markers[ii].setMap(null);
					
				}
			}
		}
		else 
		{
			for(var ii=0; ii<listObj.length; ii++)
			{
				if(listObj[ii].cable ==0 )
				{
			
					listObj[ii].visibilityCounter++;
					if(listObj[ii].visibilityCounter ==1 )
					{
						markers[ii].setMap(map);
					}
				
					
				}
			}
		}
	});


	var uscOwnedCheckboxChecked=false;
	$("#uscOwnedCheckbox").click(function(){
		

		uscOwnedCheckboxChecked= !uscOwnedCheckboxChecked;

		if(uscOwnedCheckboxChecked)
		{
			for(var ii=0; ii<listObj.length; ii++)
			{
				if(listObj[ii].uscOwned ==0)
				{
					listObj[ii].visibilityCounter--;
					markers[ii].setMap(null);
					
				}
			}
		}
		else 
		//acCheckbox is now unchecked so add 1 back to the visibility counters of all the houses that you 
		//had subtracted 1 from because they didn't have air conditioning.
		{
			for(var ii=0; ii<listObj.length; ii++)
			{
				if(listObj[ii].uscOwned ==0 )
				{
			
					listObj[ii].visibilityCounter++;
					if(listObj[ii].visibilityCounter ==1 )
					{
							markers[ii].setMap(map);
					}
				
					
				}
			}
		}
	});








	/*var petsAllowedCheckboxChecked=false;
	$("#petsAllowedCheckbox").click(function(){
		
		//if petsAllowedCheckboxChecked is false, then once you click on it it becomes true
		//or if petsAllwoedCheckBoxedChecked is true, then once you click to uncheck it, it becomes false
		petsAllowedCheckboxChecked= !petsAllowedCheckboxChecked;

		if(petsAllowedCheckboxChecked)
		{
			for(var ii=0; ii<listObj.length; ii++)
			{
				if(listObj[ii].petsAllowed ==0)
				{
					listObj[ii].visibilityCounter--;
					markers[ii].setMap(null);
					
				}
			}
		}
		else //readd the markers that the petsALlowedCheckbox took out
		{
			for(var ii=0; ii<listObj.length; ii++)
			{
				if(listObj[ii].petsAllowed ==0 )
				{
					//When you checked the pettsAllowed filter, it subtracted 1 from
					//the visibility counter of the houses that did not allow pets.  Now
					//that you deselect the petsAllowed filter, you should add 1 back to the
					//houses' visibility counters that you had subtracted 1 from for this particular filter.
					//You don't want to add 1 to all of the houses' visibility counters because
					//some houses' visibility counters were not affected when this filter was selected.
					listObj[ii].visibilityCounter++;
					if(listObj[ii].visibilityCounter ==1 )
					{
							markers[ii].setMap(map);
					}
				
					
				}
			}
		}
	});*/

	/*$("#maxDistance").slider({
    //this gets a live reading of the value and prints it on the page
    slide: function(event, ui) {
      $("#sliderResult").text(ui.value);
    }
    //this updates the value of your hidden field when user stops dragging
   
  });*/

});
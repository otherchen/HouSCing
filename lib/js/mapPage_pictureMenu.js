$(document).ready(function(){

	var isShown =false;
	$(".eachHouse").on("click", function(){

		if(!isShown)
		{
			$(this).next().show();
		}
		else
		{
			$(this).next().hide();
		}
		

		isShown=!isShown;

	});


});
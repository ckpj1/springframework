jQuery(document).ready(function() {
	
	jQuery("body").css("display", "none");

	jQuery("body").fadeIn(2000);
    
	jQuery("a.transition").click(function(event){
		event.preventDefault();
		linkLocation = this.href;
		jQuery("body").fadeOut(1000, redirectPage);		
	});
		
	function redirectPage() {
		window.location = linkLocation;
	}
	
});

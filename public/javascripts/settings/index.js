$(document).ready(function(){
    $("select[name*='userName']" ).change(function() {

        location.href ="/settings/index?userName="+$(this).val();
    });

    // This will run every 5 seconds
    window.onload = getSettings(); 
	setInterval(function() {
	    getSettings();
	}, 5000);

});

function  getSettings() {
    $.get("/settingsApi/index?userName="+userName, function(robots) {        

        document.getElementById("datatable").innerHTML = robots.settingsBodyHtml;
        document.getElementById("currentDateTime").innerHTML = robots.currentDateTime;
        //
   });
} 
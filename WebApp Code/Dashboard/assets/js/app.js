// Foundation JavaScript
// Documentation can be found at: http://foundation.zurb.com/docs

StatusEnum = {
    ROUTE : 0,
    PROGRESS : 1,
    COMPLETED : 2
}

standardErrorMessage="Oops! There seems to be some problem. Please try again later.";
standardErrorDuration=2;
standardSuccessMessage="Operation Successful!";
standardSuccessDuration=2;

CP=null;

Parse.initialize("s1z27FruCTuXoiupts6PXKsrYMYUnvm5W9QhpouM", "gnVsxFv6Da3BkCLaEgGaaEF41jj15sGDkDQ48Gcw");

function getDefaultIcon(type){
	return "./assets/images/place.png";
}

function timeTo(date) {

    var seconds = Math.floor(Math.abs(date - new Date()) / 1000);

    var interval = Math.floor(seconds / 31536000);

    if (interval > 1) {
        return interval + " years";
    }
    interval = Math.floor(seconds / 2592000);
    if (interval > 1) {
        return interval + " months";
    }
    interval = Math.floor(seconds / 86400);
    if (interval > 1) {
        return interval + " days";
    }
    interval = Math.floor(seconds / 3600);
    if (interval > 1) {
        return interval + " hours";
    }
    interval = Math.floor(seconds / 60);
    if (interval > 1) {
        return interval + " minutes";
    }
    return Math.floor(seconds) + " seconds";
}

function timeSince(date) {

    var seconds = Math.floor(Math.abs(new Date() - date) / 1000);

    var interval = Math.floor(seconds / 31536000);

    if (interval > 1) {
        return interval + " years";
    }
    interval = Math.floor(seconds / 2592000);
    if (interval > 1) {
        return interval + " months";
    }
    interval = Math.floor(seconds / 86400);
    if (interval > 1) {
        return interval + " days";
    }
    interval = Math.floor(seconds / 3600);
    if (interval > 1) {
        return interval + " hours";
    }
    interval = Math.floor(seconds / 60);
    if (interval > 1) {
        return interval + " minutes";
    }
    return Math.floor(seconds) + " seconds";
}

Date.prototype.subtractHours= function(h){
    this.setHours(this.getHours()-h);
    return this;
}

function loadingButton_id(id,d){
	var Original=document.getElementById(id).value;
	console.log("Original: "+Original );
	document.getElementById(id).value = "Processing...";
	$("#"+id).addClass('loading');
	var ref=this;
	setTimeout(function() {
		$("#"+id).removeClass('loading');
		console.log("Changing value to "+Original);
		document.getElementById(id).value = Original;
	}, d*1000);
	//console.log("Loading Button was Called!");
}
function loadingButton_id_stop(id,value){
	var Original=value;
	$("#"+id).removeClass('loading');
	document.getElementById(id).value = Original;
}

function loadingButton_ref(d){
	var Original=document.getElementById(this.id).value;
	document.getElementById(this.id).value = "Processing...";
	$(this).addClass('loading');
	setTimeout(function() {
		$(this).removeClass('loading');
		document.getElementById(this.id).value = Original;
	}, d*1000);
	console.log("Loading Button was Called!");
}

$('.interactiveLoading').click(function() {
	var Original=document.getElementById(this.id).value;
	document.getElementById(this.id).value = "Processing...";
	$(this).addClass('loading');
	var ref=this;
	setTimeout(function() {
		$(ref).removeClass('loading');
		document.getElementById(ref.id).value = Original;
	}, 12000);
	console.log("Loading Button was Called!");
});

function notify(text,type,duration){
	 NProgress.done();
	$('.alert-box').fadeIn().addClass(type).removeClass('alert').html(text + '<a href="#" class="close">&times;</a>');
	//Types are: alert, success, warning, info 
	setTimeout(function() {
		$('.alert-box').fadeOut().html('loading <a href="#" class="close">&times;</a>');
	}, duration*1000);
	$(document).on('close.alert', function(event) {
  $('#alert-hook').html('<div data-alert id="alert-box" class="alert-box-wrapper alert-box alert radius" style="display:none;"> Loading... <a href="#" class="close">&times;</a> </div>');
});
}

function icon_bg(){
	var iconBg = $('.icon-bg');
	var iconArray = ['clock',
	'location',
	'phone',
	'trophy',
	'network',
	'map-streamline-user',
	'person',
	'milestone',
	'android',
	'map'

	];
	var arraySize=iconArray.length;
	for (i = 1; i < arraySize; i++) { 
		var randomNumber = Math.floor(Math.random()*arraySize);
		iconBg.append('<i class="icon-'+iconArray[randomNumber]+'" style="font-size:'+Math.floor((Math.random() * 3) + 1.75)+'em; padding-top:'+Math.floor((Math.random() * 2) + 0.25)+'em;"></i>');
		iconArray.splice(randomNumber, 1);
	}
	console.log('success');


}
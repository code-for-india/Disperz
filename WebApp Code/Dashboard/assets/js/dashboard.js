//var constituency;
var timelineView = $('#timeline-view');

var box_pr = document.getElementById('route_it');
var box_rv = document.getElementById('prog_it');
var box_cl = document.getElementById('completed_it');

var currmarker;
var markers = [];
var singlemarker;

var infowindow;

var iconURLPrefix = './assets/images/';
var width = 40;
var height = 40;
var anchor_left = 20;
var anchor_top = 40;
var icons_url = [
    iconURLPrefix + 'marker-1.png',
    iconURLPrefix + 'marker-2.png',
    iconURLPrefix + 'marker-3.png',
    iconURLPrefix + 'marker-4.png'
];

var icon1 = {
    url: icons_url[0], // url
    scaledSize: new google.maps.Size(width, height), // size
    origin: new google.maps.Point(0, 0), // origin
    anchor: new google.maps.Point(anchor_left, anchor_top) // anchor 
};
var icon2 = {
    url: icons_url[1], // url
    scaledSize: new google.maps.Size(width, height), // size
    origin: new google.maps.Point(0, 0), // origin
    anchor: new google.maps.Point(anchor_left, anchor_top) // anchor 
};
var icon3 = {
    url: icons_url[2], // url
    scaledSize: new google.maps.Size(width, height), // size
    origin: new google.maps.Point(0, 0), // origin
    anchor: new google.maps.Point(anchor_left, anchor_top) // anchor 
};
var icon4 = {
    url: icons_url[3], // url
    scaledSize: new google.maps.Size(width, height), // size
    origin: new google.maps.Point(0, 0), // origin
    anchor: new google.maps.Point(anchor_left, anchor_top) // anchor 
};

var icons = [
    icon1,
    icon2,
    icon3,
    icon4
];

function getQueryVariable(variable){
   var query = window.location.search.substring(1);
   var vars = query.split("?");
   for (var i=0;i<vars.length;i++) {
           var pair = vars[i].split("=");
           if(pair[0] == variable){return pair[1];}
   }
   return(false);
}

// Creates the Array of Constituencies
function fetchPlace(id) {
    console.log("fetchPlace");
    ListItem = Parse.Object.extend("Places");
    query = new Parse.Query(ListItem);
    query.find({
        success: function(results) {
            for (var i = 0; i < results.length; i++) {
                object = results[i];
                if(object.id==id){
                    CP=object;
                    initializeMap();
                }
            }
        },
        error: function(error) {
            console.log("Error: " + error.message);
            notify(standardErrorMessage, "error", standardErrorDuration);
        }
    });
}

// Sets the map on all markers in the array.
function setAllMap(map) {
    console.log("setAllMap");
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}

// Removes the markers from the map, but keeps them in the array.
function clearMarkers() {
    console.log("clearMarkers");
    setAllMap(null);
}

// Shows any markers currently in the array.
function showMarkers() {
    console.log("showMarkers");
    setAllMap(map);
}

// Deletes all markers in the array by removing references to them.
function deleteMarkers() {
    console.log("deleteMarkers");
    clearMarkers();
    markers = [];
}

// 
function fetchUsers() {
    console.log("populateUpdates");
    timelineView.html("");
    ListItem = Parse.Object.extend("Tours");
    query = new Parse.Query(ListItem);
    var pointer = new Parse.Object("Places");
    pointer.id = CP.id;
    query.equalTo("place", pointer);
    query.include("user");
    query.ascending('createdAt');
    query.find({
        success: function(results) {
            console.log("Size:" + results.length);
            for (var i = 0; i < results.length; i++) {
                object = results[i];
                console.log(object);
                order=object.get("order_navpoints");

                completed=object.get("bool_checkpoints");
                content="<i>Order:"+order+"</i><br><b>Visited:"
                for(var j=0;j<order.length;j++){
                    if(completed[j]==1){
                        content=content+order[j]+"->";
                    }
                }
                content=content+"</b><br>"
                marker = new google.maps.Marker({
                    position: {
                        lat: object.get("user").get('location').latitude,
                        lng: object.get("user").get('location').longitude
                    },
                    map: map,
                    title: object.get('category'),
                    content: object,
                    draggable: false,
                    animation: google.maps.Animation.DROP
                });
                markers.push(marker);
                google.maps.event.addListener(marker, 'click', (function(marker, object) {
                    return function() {
                        NProgress.start();
                        console.log("NProgress start");
                        if (infowindow) {
                            infowindow.close();
                        }
                        infowindow = new google.maps.InfoWindow({
                            maxWidth: 800,
                            maxHeight: 900
                        });
                        currmarker = marker;
                        infowindow.setContent(content);
                        infowindow.open(map, currmarker);
                        NProgress.done();
                    }
                })(marker, object));
                filter();
                updateCounters();
            }
            NProgress.done();
            console.log("NProgress Stop");

        },
        error: function(error) {
            console.log("Error: " + error.message);
            notify(standardErrorMessage, "error", standardErrorDuration);
        }
    });
}

function statusCounters(no, np, nr) {
    console.log("statusCounter");
    var numAnim1 = new countUp("fn1", 0, no);
    numAnim1.start();
    var numAnim2 = new countUp("fn2", 0, np);
    numAnim2.start();
    var numAnim3 = new countUp("fn3", 0, nr);
    numAnim3.start();
}

function statusCheck(m) {
    console.log("StatusCheck");
    if ((m.content).get("status") == "enroute") {
        if (box_pr.checked) {
            return 1;
        }
    }
    if ((m.content).get("status") == "progress") {
        if (box_rv.checked) {
            return 1;
        }
    }
    if ((m.content).get("status") == "completed") {
        if (box_cl.checked) {
            return 1;
        }
    }
    return 0;
}

function filter() {
    console.log("filter");
    for (var m = 0; m < markers.length; m++) {
        if (statusCheck(markers[m]) == 1){
            markers[m].setMap(map);
        } else {
            markers[m].setMap(null);

        }
    }
}


function updateCounters() {
    var no = 0;
    var np = 0;
    var nr = 0;
    for (var m = 0; m < markers.length; m++) {
        if (markers[m].getMap() != null) {
            if ((markers[m].content).get('status') == "enroute") {
                no = no + 1;
            }
            if ((markers[m].content).get('status') == "progress") {
                np = np + 1;
            }
            if ((markers[m].content).get('status') == "completed") {
                nr = nr + 1;
            }
        }
    }
    statusCounters(no, np, nr);
}

function initializeMap() {
    map.setCenter(new google.maps.LatLng(CP.get("location").latitude, CP.get("location").longitude));    
    marker1 = new google.maps.Marker({
        position: {
            lat: CP.get('checkpoints')[0].latitude,
            lng: CP.get('checkpoints')[0].longitude
        },
        map: map,
        title: "1",
        content: "1",
        icon: icon1,
        draggable: false,
        animation: google.maps.Animation.DROP
    });
    marker1.setMap(map);
    marker2 = new google.maps.Marker({
        position: {
            lat: CP.get('checkpoints')[1].latitude,
            lng: CP.get('checkpoints')[1].longitude
        },
        map: map,
        title: "2",
        content: "2",
        icon: icon2,
        draggable: false,
        animation: google.maps.Animation.DROP
    });
    marker2.setMap(map);
    marker3 = new google.maps.Marker({
        position: {
            lat: CP.get('checkpoints')[2].latitude,
            lng: CP.get('checkpoints')[2].longitude
        },
        map: map,
        title: "3",
        content: "3",
        icon: icon3,
        draggable: false,
        animation: google.maps.Animation.DROP
    });
    marker3.setMap(map);
    marker4 = new google.maps.Marker({
        position: {
            lat: CP.get('checkpoints')[3].latitude,
            lng: CP.get('checkpoints')[3].longitude
        },
        map: map,
        title: "4",
        content: "4",
        icon: icon4,
        draggable: false,
        animation: google.maps.Animation.DROP
    });
    marker4.setMap(map);
    fetchUsers();
}

function initialize() {
    console.log("initialize");
    placeId= getQueryVariable("id");
    CP = fetchPlace(placeId);

    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 16,
        center: new google.maps.LatLng(28.612912, 77.22951),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    $('input[type=checkbox]').change(
        function() {
            NProgress.start();
            console.log("NProgress Start");
            if (infowindow) {
                infowindow.close();
            }
            filter();

            setTimeout(function() {
                google.maps.event.trigger(map, 'resize');
                map.setZoom(map.getZoom());
            }, 700);

            NProgress.done();
            console.log("NProgress Stop");
        });

    $('input[name=maptglgroup]').change(function() {
        NProgress.start();
        console.log("NProgress Start");
        enableCheckPoints();
        if (infowindow) {
            infowindow.close();
        }
    });
}
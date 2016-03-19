var places=[];

var places=$("#place-view");

function updateCP(id){
    ListItem = Parse.Object.extend("Places");
    query = new Parse.Query(ListItem);
    query.descending('createdAt');
    query.find({
      success: function(results) {
        for (var i = 0; i < results.length; i++) { 
            var object = results[i];
            if(object.id==id){
                CP=object;
                document.location.href = "localhost:4000/dashboard.html";
            }
        }
        },
        error: function(error){

        }
    });
}

function queryPlacesTable(){
    console.log('QueryPlacesTable');
    ListItem = Parse.Object.extend("Places");
    query = new Parse.Query(ListItem);
    query.descending('createdAt');
    query.find({
          success: function(results) {
                for (var i = 0; i < results.length; i++) { 
                    var object = results[i];
                    var image = object.get("image").url();
                    var name = object.get("name");
                    var strength=object.get("strength");
                    var balance=object.get("balance");
                    var time=timeSince(object.get("updatedAt"));
                    var id=object.id;
                    ListItem2 = Parse.Object.extend("Tour"); 
                    query2 = new Parse.Query(ListItem2);
                    place_pointer = Parse.Object.extend("Places");
                    place_pointer.id=id;
                    // Fetch Number of People at the Location
                    // Fetch the Balance Ratio
                    places.append("<a href='http://localhost:4000/dashboard.html?id="+id+"'><div id='place-"+id+"' class='row'><div class='panel nm br-fx-bottom'><div class='row'><div class='small-3 small-offset-6 columns text-right secondary-color s-ws-bottom'><span class='tertiary'>Crowd: </span><span class='secondary-color tertiary'>"+strength+"</span></div><div class='small-3 columns secondary-color tertiary text-right s-ws-bottom'><i class='icon-clock tertiary'></i>"+time+"</div></div><div class='row'><div class='small-6 columns s-ws-bottom' align='center'><img id='photo' src='"+image+"' width='30%' class='circle-img'><p align='center'>"+name+"</p></div><div class='small-6 columns'><p class=''><div class='small-12 columns m-ws-top'><div id='support' class='l-lc text-right secondary-color ct secondary' style='width:"+balance+"%'>Crowd</div><div id='skeptics' class='d-lc secondary-color ct secondary' style='width:"+(99-balance)+"%'>Balance</div></div></p></div></div></div></div></a>");
                }
                NProgress.done();
            },
          error: function(error) {
                //console.log("Error: "+error.message);
                notify(error.message, "error",standardErrorDuration);
          }
    });
}

function initialize() {
    console.log("initialize");
    NProgress.start();
    console.log("NProgress Start");
    queryPlacesTable();
}

initialize();

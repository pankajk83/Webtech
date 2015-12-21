<?php
//if (is_ajax()) {
 
 if (isset($_GET["action"]) && !empty($_GET["action"])) { //Checks if action value exists
  $action = $_GET["action"];
    switch($action) { 
      case "test": test_function(); break;
    }
 // }
}

//Function to check if the request is an AJAX request
function is_ajax() {
  return isset($_SERVER['HTTP_X_REQUESTED_WITH']) && strtolower($_SERVER['HTTP_X_REQUESTED_WITH']) == 'xmlhttprequest';
}
function test_function(){
  
$return = $_GET;
$data_arr = geocode($_GET['street'], $_GET['city'], $_GET['state']);
  if ( $data_arr == false) {
  exit("Google geocode api returns ZERO Results: Select correct address");
  }	
        $latitude = $data_arr[0];
        $longitude = $data_arr[1];
	$furl="https://api.forecast.io/forecast/96684c2d23e7643e7c49ecbcfa131e5f/{$latitude},{$longitude}?units={$_GET['degree']}&exclude=flags";
//error_log($furl, 0);

  $resp_json = file_get_contents($furl);
  // decode the json
  $respjson = json_decode($resp_json, true);
  $return["json"] = json_encode($respjson);
  echo json_encode($return);
}

function geocode($street,$city,$state){
	$faddress=$street.",".$city.",".$state;
	$fadd = urlencode($faddress);
	$geoURL="https://maps.googleapis.com/maps/api/geocode/xml?address={$fadd}&key=AIzaSyBm2wQkE9_C8s9Aw3_nrlON6c2wl-6AaQ0";

	$xmlStr = file_get_contents($geoURL);
	$resp = simplexml_load_string($xmlStr);

 	if ($resp->status=='OK') {
        // get the important data
	$lati=$resp->result[0]->geometry[0]->location[0]->lat;
	$longi= $resp->result[0]->geometry[0]->location[0]->lng;
	// verify if data is complete
        	if($lati && $longi) {
              		$data_arr = array();           
             
           		 array_push(
          		      $data_arr,
                    		$lati,
                 		   $longi                    
               			 );
             
            		return $data_arr;
             
        		}else{
          		  return false;
         		} 
		}       
	}
?>




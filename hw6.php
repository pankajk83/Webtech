<html>
<head>
<meta charset="utf-8">
<title>HW6</title>

			
        <style type="text/css">
            .search {
                height: auto;
                width: 500px;
                border: 4px solid black;
                margin: 10 auto;
                padding: 20px;
            }

		.output1 {
                height: auto;
                width: 500px;
                border: 4px solid black;
                margin: 10 auto;
                padding: 20px;
            }

            .input-rounded-button {
                -webkit-border-radius: 3px;
                -moz-border-radius: 3px;
                border-radius: 3px;
                border: 1px solid gray;
                font-size: 16px;
                cursor: pointer;
            }
	

</style>
<script type="text/javascript" >
function cancel(){
alert("GeocodeResponse is zero");
return;
}
function clearForm(ele) {
document.forms["searchForm"]["address"].value = ''; 
document.forms["searchForm"]["city"].value = '';
document.forms["searchForm"]["state"].value = 'default';
document.forms["searchForm"]["unit"].value = 'us';
document.getElementById('faren').checked=true;
document.getElementById('output').innerHTML = '';


}
function validate(){
    
if((document.forms["searchForm"]["address"].value == "") || (document.forms["searchForm"]["address"].value == null)){
     alert("Please enter value for street address");
     return false;
}

if((document.forms["searchForm"]["city"].value == "") || (document.forms["searchForm"]["city"].value == null)){
     alert("Please enter value for city");
     return false;
}
if((document.forms["searchForm"]["state"].value == "default")){
     alert("Please select value for state");
     return false;
}

}
</script>
    </head>

    <body >
        <div style="text-align: center;"><h2>Forecast Search</h2></div>

        <div class="search">
            <form name="searchForm"  method="POST" onSubmit="return validate()" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">

  <table>
	<tr>
                <td><label>Street Address:*</label></td>
		<td> <input class="text" type="text" size="25" name="address" value="<?php if ($_SERVER["REQUEST_METHOD"] == "POST") {echo $_POST['address']; } ?>"></td>
       </tr>
	
	<tr>
		<td><label>City:*</label> </td>
		<td><input class="text" type="text" size="25" name="city" value="<?php if ($_SERVER["REQUEST_METHOD"] == "POST") {echo $_POST['city']; } ?>"></td>
	</tr>

	<tr>
            <td> <label>State:*</label> </td>
	    <td> <select class="text" name="state" id="dropdown">
                    <option value="default" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "default"){ echo "selected"; } } ?>selected>Select your state</option>
                    <option value="AL" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "AL"){ echo "selected"; } } ?>>Alabama</option>
<option value="AK" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "AK"){ echo "selected"; } } ?>>Alaska</option>
<option value="AZ" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "AZ"){ echo "selected"; } } ?>>Arizona</option>
<option value="AR" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "AR"){ echo "selected"; } } ?>>Arkansas</option>
<option value="CA" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "CA"){ echo "selected"; } } ?>>California</option>
<option value="CO" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "CO"){ echo "selected"; } } ?>>Colorado</option>
<option value="CT" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "CT"){ echo "selected"; } } ?>>Connecticut</option>
<option value="DE" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "DE"){ echo "selected"; } } ?>>Delaware</option>
<option value="DC" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "DC"){ echo "selected"; } } ?>>DistrictOfColumbia</option>
<option value="FL" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "FL"){ echo "selected"; } } ?>>Florida</option>
<option value="GA" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "GA"){ echo "selected"; } } ?>>Georgia</option>
<option value="HI" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "HI"){ echo "selected"; } } ?>>Hawaii</option>
<option value="ID" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "ID"){ echo "selected"; } } ?>>Idaho</option>
<option value="IL" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "IL"){ echo "selected"; } } ?>>Illinois</option>
<option value="IN" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "IN"){ echo "selected"; } } ?>>Indiana</option>
<option value="IA" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "IA"){ echo "selected"; } } ?>>Iowa</option>
<option value="KS" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "KS"){ echo "selected"; } } ?>>Kansas</option>
<option value="KY" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "KY"){ echo "selected"; } } ?>>Kentucky</option>
<option value="LA" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "LA"){ echo "selected"; } } ?>>Louisiana</option>
<option value="ME" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "ME"){ echo "selected"; } } ?>>Maine</option>
<option value="MD" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "MD"){ echo "selected"; } } ?>>Maryland</option>
<option value="MA" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "MA"){ echo "selected"; } } ?>>Massachusetts</option>
<option value="MI" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "MI"){ echo "selected"; } } ?>>Michigan</option>
<option value="MN" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "MN"){ echo "selected"; } } ?>>Minnesota</option>
<option value="MS" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "MS"){ echo "selected"; } } ?>>Mississippi</option>
<option value="MO" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "MO"){ echo "selected"; } } ?>>Missouri</option>
<option value="MT" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "MT"){ echo "selected"; } } ?>>Montana</option>
<option value="NE" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "NE"){ echo "selected"; } } ?>>Nebraska</option>
<option value="NV" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "NV"){ echo "selected"; } } ?>>Nevada</option>
<option value="NH" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "NH"){ echo "selected"; } } ?>>NewHampshire</option>
<option value="NJ" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "NJ"){ echo "selected"; } } ?>>NewJersey</option>
<option value="NM" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "NM"){ echo "selected"; } } ?>>NewMexico</option>
<option value="NY" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "NY"){ echo "selected"; } } ?>>NewYork</option>
<option value="NC" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "NC"){ echo "selected"; } } ?>>NorthCarolina</option>
<option value="ND" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "ND"){ echo "selected"; } } ?>>NorthDakota</option>
<option value="OH" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "OH"){ echo "selected"; } } ?>>Ohio</option>
<option value="OK" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "OK"){ echo "selected"; } } ?>>Oklahoma</option>
<option value="OR" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "OR"){ echo "selected"; } } ?>>Oregon</option>
<option value="PA" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "PA"){ echo "selected"; } } ?>>Pennsylvania</option>
<option value="RI" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "RI"){ echo "selected"; } } ?>>RhodeIsland</option>
<option value="SC" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "SC"){ echo "selected"; } } ?>>SouthCarolina</option>
<option value="SD" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "SD"){ echo "selected"; } } ?>>SouthDakota</option>
<option value="TN" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "TN"){ echo "selected"; } } ?>>Tennessee</option>
<option value="TX" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "TX"){ echo "selected"; } } ?>>Texas</option>
<option value="UT" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "UT"){ echo "selected"; } } ?>>Utah</option>
<option value="VT" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "VT"){ echo "selected"; } } ?>>Vermont</option>
<option value="VA" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "VA"){ echo "selected"; } } ?>>Virginia</option>
<option value="WA" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "WA"){ echo "selected"; } } ?>>Washington</option>
<option value="WV" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "WV"){ echo "selected"; } } ?>>WestVirginia</option>
<option value="WI" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "WI"){ echo "selected"; } } ?>>Wisconsin</option>
<option value="WY" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['state'] == "WY"){ echo "selected"; } } ?>>Wyoming
                </select>
	   </td>
	</tr>

	<tr>
              <td>  <label>Degree:*</label> </td>
	      <td> <input  type="radio" checked name="unit" id="faren" value="us" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['unit'] == "us"){ echo "checked"; } } ?>><label>Fahrenheit</label>
              <input  type="radio" name="unit" value="si" style="margin-left: 10px;" <?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ if($_POST['unit'] == "si"){ echo "checked"; } } ?>><label>Celsius</label></td>

	
</table>
	     
  <div style="text-align: center; margin-top: 10px;"><input type="submit" name="submit" value="Search">
<input style="text-align: auto; margin-top: 10px;" type="button" name="clear" value="Clear" onClick="return clearForm(this.form)"></div>



 <i> *-Mandatory fields. </i><td>

            </form>
<div style="text-align: center; margin-top: 10px; font-weight:bold" id="footer">

  <a href="http://forecast.io/" title="url"><u>Powered by Forecast.io</u></a>
</div>
        </div>


<?php
//if($_POST['address'] != null or $_POST['address'] != "") {
if($_POST) {
 
    // get latitude, longitude and formatted address
    $data_arr = geocode($_POST['address'], $_POST['city'], $_POST['state']);

  
	//$data_arr = geocode($_POST['address']);

	if ( $data_arr == false) {
		//echo "Google geocode reurns ZERO Results";
		exit("Google geocode api returns ZERO Results: Select correct address");
	}	
 
    // if able to geocode the address
    if($data_arr){
         
        $latitude = $data_arr[0];
        $longitude = $data_arr[1];

	$furl="https://api.forecast.io/forecast/96684c2d23e7643e7c49ecbcfa131e5f/{$latitude},{$longitude}?units={$_POST['unit']}&exclude=flags";
	//echo $furl;
	// get the json response
    		$resp_json = file_get_contents($furl);
     
    // decode the json
    $respjson = json_decode($resp_json, true);

if(array_key_exists('icon', $respjson['currently'])) {
$icon = $respjson['currently']['icon'];
}
else{
 $icon="none";
}


if(array_key_exists('summary', $respjson['currently'])) {
$summary = $respjson['currently']['summary'];
}
else {
$summary="Clear";
}

if ($icon == "clear-day"){
	$iconSrc = "http://cs-server.usc.edu:45678/hw/hw6/images/clear.png";
	$iconMessage = "Clear Day";
}
else if ($icon == "clear-night"){
	$iconSrc = "http://cs-server.usc.edu:45678/hw/hw6/images/clear_night.png";
	$iconMessage = "Clear Night";
}
else if ($icon == "rain"){
	$iconSrc = "http://cs-server.usc.edu:45678/hw/hw6/images/rain.png";
	$iconMessage = "Rain";
}
else if ($icon == "snow"){
	$iconSrc = "http://cs-server.usc.edu:45678/hw/hw6/images/snow.png";
	$iconMessage = "Snow";
}
else if ($icon == "sleet"){
	$iconSrc = "http://cs-server.usc.edu:45678/hw/hw6/images/sleet.png";
	$iconMessage = "Sleet";
}
else if ($icon == "fog"){
	$iconSrc = "http://cs-server.usc.edu:45678/hw/hw6/images/fog.png";
	$iconMessage = "Fog";
}
else if ($icon == "cloudy"){
	$iconSrc = "http://cs-server.usc.edu:45678/hw/hw6/images/cloudy.png";
	$iconMessage = "Cloudy";
}
else if ($icon == "partly-cloudy-day"){
	$iconSrc = "http://cs-server.usc.edu:45678/hw/hw6/images/cloud_day.png";
	$iconMessage = "Partly Cloudy Day";
}
else if ($icon == "partly-cloudy-night"){
	$iconSrc = "http://cs-server.usc.edu:45678/hw/hw6/images/cloud_night.png";
	$iconMessage = "Partly Cloudy Night";
}
else {
$iconSrc = "none.png";
$iconMessage = "Icon not found on server";
}

if(array_key_exists('temperature', $respjson['currently'])) {
    $temperature= intval($respjson['currently']['temperature']);
}
else{
$temperature= 0;
}


if(array_key_exists('precipIntensity', $respjson['currently'])) {
$precipIntensity = floatval($respjson['currently']['precipIntensity']);
 
    if ( $precipIntensity >= 0 && $precipIntensity < 0.002) {
        $precipIntensityValue= "None";	
    }
    else if ($precipIntensity >= 0.002 && $precipIntensity < 0.017){
        $precipIntensityValue= "Very Light";
    }
    else if ($precipIntensity >= 0.017 && $precipIntensity < 0.1){
        $precipIntensityValue= "Light";
    }
    else if ($precipIntensity >= 0.1 && $precipIntensity < 0.4){
        $precipIntensityValue= "Moderate";
    }
    else if ($precipIntensity >= 0.4){
        $precipIntensityValue= "Heavy";
    }
   else {
	$precipIntensityValue= "None";	
}
}
else {
	$precipIntensityValue= "None";	
}

if(array_key_exists('precipProbability', $respjson['currently'])) {
$precipProbability = floatval($respjson['currently']['precipProbability']);
$precipProbabilityValue = $precipProbability*100;
$precipProbabilityValue.="%";
}
else{
$precipProbabilityValue="0%";
}
//."%";

if(array_key_exists('windSpeed', $respjson['currently'])) {
$windSpeed = intval($respjson['currently']['windSpeed']);
$windSpeedValue = $windSpeed;
$windSpeedValue.=" mph";
}
else{
$windSpeedValue="0 mph";
}

if(array_key_exists('dewPoint', $respjson['currently'])) {
$dewPointValue = intval($respjson['currently']['dewPoint']);
}
else{
$dewPointValue="0";
}

if(array_key_exists('humidity', $respjson['currently'])) {
$humidity = floatval($respjson['currently']['humidity']);
$humidityValue = $humidity*100;
$humidityValue.="%";
}
else{
$humidityValue="0%";
}

if(array_key_exists('visibility', $respjson['currently'])) {
$visibility = intval($respjson['currently']['visibility']);
$visibilityValue = $visibility;
$visibilityValue.=" mi";

}
else{
$visibilityValue="0 mi";
}
//date_default_timezone_set('UTC');
//timezone
if(array_key_exists('timezone', $respjson)) {
    $timezone= $respjson['timezone'];
}
else{
$timezone= 'America/Los_Angeles';
}
date_default_timezone_set($timezone);

if(array_key_exists('sunriseTime', $respjson['daily']['data'][0])) {
$sunriseTime = date("H:i A",$respjson['daily']['data'][0]['sunriseTime']);
}
else{
$sunriseTime="N/A";
}

if(array_key_exists('sunsetTime', $respjson['daily']['data'][0])) {
$sunsetTime = date("H:i A",$respjson['daily']['data'][0]['sunsetTime']);
}
else{
$sunsetTime="N/A";
}



 if($_POST['unit'] == "us") {
 $unit = "F";
}
if($_POST['unit'] == "si") {
  $unit = "C";
}
    // response status will be 'OK', if able to geocode given address
   	
 
        // print_r($respjson);

	
	
}
}
                     
?>


<?php if ($_POST && $data_arr != false): ?>
<div id="output">
<div class="output1" >
<table >
<tr><td></td><td style="padding-left: 40px"><b><?php echo $summary ?></b></td></tr>
<tr><td></td><td style="padding-left: 40px"><b><?php echo $temperature ?>&deg;<?php echo $unit ?></b></td></tr>
<tr><td></td><td style="padding-left: 40px"><img src="<?php echo $iconSrc ?>" title="<?php echo $summary ?>" alt = "<?php echo $summary ?>" style="width:85px;height:50px;">
</td></tr>
<tr>
<td style="padding-left: 30px"><label>Precipitation:</label></td> 
<td style="padding-left: 150px"><?php echo $precipIntensityValue ?><td>
</tr>
<tr>
<td style="padding-left: 30px"><label>Chance of Rain:</label></td> 
 
<td style="padding-left: 150px"><?php echo $precipProbabilityValue ?><td>
</tr>
<tr>
<td style="padding-left: 30px"><label>Wind Speed:</label></td> <td style="padding-left: 150px"><?php echo $windSpeedValue ?><td>
</tr>

<tr>
<td style="padding-left: 30px"><label>Dew Point:</label></td> <td style="padding-left: 150px"><?php echo $dewPointValue ?><td>
</tr>

<tr>
<td style="padding-left: 30px"><label>Humidity:</label></td> <td style="padding-left: 150px"><?php echo $humidityValue ?><td>
</tr>

<tr>
<td style="padding-left: 30px"><label>Visibility:</label></td> <td style="padding-left: 150px"><?php echo $visibilityValue ?><td>
</tr>

<tr>
<td style="padding-left: 30px"><label>Sunrise:</label></td> <td style="padding-left: 150px"><?php echo $sunriseTime ?><td>
</tr>

<tr>
<td style="padding-left: 30px"><label>Sunset:</label></td> <td style="padding-left: 150px"><?php echo $sunsetTime ?><td>
</tr>


</table>
</div>
</div>

<?php endif; ?>

<?php
 
// function to geocode address, it will return false if unable to geocode address
function geocode($address,$city,$state){
//function geocode($address){
//96684c2d23e7643e7c49ecbcfa131e5f   forecast api key
//https://api.forecast.io/forecast/96684c2d23e7643e7c49ecbcfa131e5f/$latitude,$longitude?units=$_POST['unit']&exclude=flags

 
    // url encode the address
   
	$faddress=$address.",".$city.",".$state;
	$fadd = urlencode($faddress);
	//$fadd = urlencode($address);
	//echo $fadd;

     
    // google map geocode api url
   $geoURL="https://maps.googleapis.com/maps/api/geocode/xml?address={$fadd}&key=AIzaSyBm2wQkE9_C8s9Aw3_nrlON6c2wl-6AaQ0";

	//echo $geoURL;
 	
    // get the json response
   // $resp_json = file_get_contents($url);

	$xmlStr = file_get_contents($geoURL);
	$resp = simplexml_load_string($xmlStr);
	
/*
GeocodeResponse><status>OK</status>
*/

	//echo "pamnakjjjjjjjjjjjjjjjjjj";
	//$type =  $resp->status;
	//echo $type;
 	if ($resp->status=='OK'){
        // get the important data
	$lati=$resp->result[0]->geometry[0]->location[0]->lat;
	$longi= $resp->result[0]->geometry[0]->location[0]->lng;
	

	// verify if data is complete
        if($lati && $longi){
         
            // put the data in the array
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


    </body>

</html>

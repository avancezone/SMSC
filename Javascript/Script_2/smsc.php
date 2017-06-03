<?php
/* ENBALE IN PROD WITHOUT FAIL
$ref = $_SERVER['HTTP_REFERER'];
$refData = parse_url($ref);

if($refData['host'] !== 'domain.com') {
  // Output string and stop execution
  die("Hotlinking not permitted");
}
*/
error_reporting(0);
//API Details
$username ="USERNAME";
$password ="PASSWORD";
$mob_number=$_GET['mob_number'];
// Sender ID
$approved_senderid="SENDERID";

//Approved Template
$message = $_GET['message'];
$message= rawurlencode($message); // Encoded message

//Create API URL
$fullapiurl="https://securesmsc.com/httpapi/send?username=$username&password=$password&sender_id=$approved_senderid&route=T&phonenumber=$mob_number&message=$message";

//Call API
//echo $fullapiurl;exit;
$ch = curl_init($fullapiurl);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
$result = curl_exec($ch); 
echo $result ; // For Report or Code Check
curl_close($ch);
?>
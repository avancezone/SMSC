<?php
//API Details
$username ="USERNAME";
$api_key ="YOURKEY";
$mob_number="MOBILENUMBER";
// Sender ID
$approved_senderid="SENDERID";

//Approved Template
$message = "YOUR APPROVED TEMPLATE";
$enc_msg= rawurlencode($message); // Encoded message

//Create API URL
$fullapiurl="http://smsc.biz/httpapi/send?api_key=$api_key&to=$mob_number&sender=$approved_senderid&message=$enc_msg";

//Call API
$ch = curl_init($fullapiurl);
// SSL - curl_setopt($ch, CURLOPT_VERBOSE, true);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
// SSL - curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
$result = curl_exec($ch); 
// DEBUG - echo $result ; // For Report or Code Check
curl_close($ch);
echo "<p>SMS Request Sent - Message id - $result </p>";
?>
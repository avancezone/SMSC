<?php
// Tamil - இந்த மொழி Tamil
// UTF-16BE - 0B870BA80BCD0BA400200BAE0BCA0BB40BBF002000540061006D0069006C

function convertCharsn($string) {
		$in = '';
		$out = iconv('UTF-8', 'UTF-16BE', $string);
		for($i=0; $i<strlen($out); $i++) {
		$in .= sprintf("%02X", ord($out[$i]));
		}
		return $in;
}
$unicode_chars = "இந்த மொழி Tamil";
$UTF16BE_chars = convertCharsn(html_entity_decode($unicode_chars, ENT_QUOTES | 'ENT_HTML401', 'UTF-8'));

//API Details
$username ="USERNAME";
$password ="PASSWORD";
$mob_number="############";
// Sender ID
$approved_senderid="PROMOTIONAL";

//Approved Template
$message = $UTF16BE_chars;
$enc_msg= rawurlencode($message); // Encoded message

//Create API URL
$fullapiurl="http://smsc.biz/httpapi/send?username=$username&password=$password&sender_id=$approved_senderid&route=P&phonenumber=$mob_number&message=$enc_msg&type=2";
//Call API
$ch = curl_init($fullapiurl);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$result = curl_exec($ch); 
//echo $result ; // For Report or Code Check
curl_close($ch);
echo "<p>SMS Request Sent - Message id - $result </p>";
?>

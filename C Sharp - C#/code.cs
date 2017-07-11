using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Net;

//Your Username key
string username = "USERNAME";
//Your Password key
string password = "PASSWORD";
//Multiple mobiles numbers separated by comma
string mobileNumber = "MOB NUMBER";
//Sender ID - Approved in your account.
string senderId = "APPORVED_SENDER ID";
//Route - T for Transactional / P for Promotional
string route = "T";
//Your Approved message to send.
string message = HttpUtility.UrlEncode("YOUR APPROVED TEMPLATE");
try
{
	//Call Send SMS API
	string sendSMSUri = "http://smsc.biz/httpapi/send?username=" + username + "&password=" + password + "&sender_id=" + senderId + "&route=" + route + "&phonenumber=" + mobileNumber + "&message=" + message;
	//Create HTTPWebrequest
	HttpWebRequest httpWReq = (HttpWebRequest)WebRequest.Create(sendSMSUri);
	var client = new WebClient();
	var content = client.DownloadString(sendSMSUri);
	System.Diagnostics.Debug.WriteLine(content);
}
catch (SystemException ex)
{
	System.Diagnostics.Debug.WriteLine(ex.Message.ToString());
}
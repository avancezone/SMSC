import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class SmsCallGet {

	/**
	 * @param args
	 */

	String username = "USERNAME";
	String password = "PASSWORD";
	String domain = "http://smsc.biz";

	public static void main(String[] args)  {
		SmsCallGet test = new SmsCallGet();
		test.sendMessage("T", "MOBILENUMBER", "YOUR APPROVED TEMPLATE", "SENDERID");

	}

	public  void sendMessage(String route, String phonenumber, String rawmessage, String sender_id)  {

		try {

			final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				//@Override
				public void checkClientTrusted(final X509Certificate[] chain, final String authType) {
				}

				//@Override
				public void checkServerTrusted(final X509Certificate[] chain, final String authType) {
				}

				//@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} };

			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
			URL url = new URL(getURLPath(domain, route,phonenumber,rawmessage, username, password, sender_id));
			System.setProperty("jsse.enableSNIExtension", "false");
			final HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();
			// System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getURLPath(String domain, String route, String phonenumber, String rawmessage, String username,
			String password, String sender_id) throws UnsupportedEncodingException {
		String twar = domain + "/httpapi/send?username=" + username + "&password=" + password
				+ "&sender_id=" + sender_id + "&route=" + route + "&phonenumber=" + phonenumber + "&message="
				+ getencoded(rawmessage).replaceAll("\\+","%20");
		//System.out.println("twar: " + twar);
		return twar;
	}

	private String getencoded(String value) throws UnsupportedEncodingException {
		String uri =URLEncoder.encode(value, "UTF-8"); 
	return uri;
	}
	

}
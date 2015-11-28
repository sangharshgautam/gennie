package uk.gov.direct.driverpracticaltest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CheckTestDate {
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0";

	private static final SimpleDateFormat format = new SimpleDateFormat("EEEE d MMMM yyyy HH:mmaaa");
	
	private static final String ROOT = "https://driverpracticaltest.direct.gov.uk";

	private static final String MANAGE_URL = ROOT + "/manage";

	public static void main(String[] args) {
		CheckTestDate test = new CheckTestDate();
		try {
			test.run();
		} catch (Exception e) {
			//e.printStackTrace();
			String msg = e.getMessage();
			if(msg == null){
				msg = e.toString();
			}
			String encoded = URLEncoder.encode(msg);
			Jsoup.connect("https://gennie-finnler.rhcloud.com/api/telegram/message/120340564?msg="+encoded);
		} 
	}

	private void run() throws ClientProtocolException, IOException, ParseException {
		HttpClient client = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
		String html = get(client, MANAGE_URL, "test1.html");
		String html2 = post(client, params(), ROOT + "/login", "test2.html");
		String currBookingDateStr = parse(html2, "section#confirm-booking-details.formatting section div.contents dl dd").get(0)
				.text();
		Date currentBookingDate = format.parse(currBookingDateStr);
		System.out.println(currBookingDateStr);
		String changeTimeLink = ROOT + parse(html2, "a#date-time-change.button").get(0).attr("href");
		System.out.println("CHNGE TIME ");
		String html3 = get(client, changeTimeLink, "test3.html");

		String formSubmitUrl = ROOT + parse(html3, "div#main section form").get(0).attr("action");
		String html4 = post(client, params2(), formSubmitUrl, "test4.html");

		Elements slots = parse(html4, "section#availability-results ul.button-board li a span");
		Element slot = slots.get(0);
		String slotDateStr = slot.text();
		System.out.println(slotDateStr);
		Date slotDate = format.parse(slotDateStr);
		if(slotDate.before(currentBookingDate)){
			String msg = "NEW Slot found "+ slotDateStr;
			String encoded = URLEncoder.encode(msg);
			get(client, "https://gennie-finnler.rhcloud.com/api/telegram/message/120340564?msg="+encoded, "nofile");
			get(client, "https://gennie-finnler.rhcloud.com/api/telegram/message/151865631?msg="+encoded, "nofile");
		}
		
		get(client, MANAGE_URL, "test5.html");
		String signOutLink = ROOT + parse(html2, "div#header-button-container a.button").get(0).attr("href");
		System.out.println("SIGNOUT");
		get(client, signOutLink, "test6.html");
	}

	private List<NameValuePair> params2() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("drivingLicenceSubmit", "ASAP"));
		params.add(new BasicNameValuePair("preferredTestDate", ""));
		params.add(new BasicNameValuePair("testChoice", "ASAP"));
		return params;
	}

	private List<NameValuePair> params() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("alternativePassword", ""));
		params.add(new BasicNameValuePair("booking-login", "Continue"));
		params.add(new BasicNameValuePair("javascriptEnabled", "true"));
		params.add(new BasicNameValuePair("password", "34161278"));
		params.add(new BasicNameValuePair("passwordType", "NORMAL"));
		params.add(new BasicNameValuePair("username", "SINGH856102A99PE"));
		return params;
	}

	private String post(HttpClient client, List<NameValuePair> urlParameters, String url, String fileName)
			throws UnsupportedEncodingException, IOException, ClientProtocolException {
		HttpPost post = new HttpPost(url);

		// add header
		post.setHeader("User-Agent", USER_AGENT);

		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(post);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		String html = getContent(response);
		writeResponse(html, fileName);
		return html;
	}

	private static String get(HttpClient client, String url, String fileName)
			throws IOException, ClientProtocolException {
		HttpGet request = new HttpGet(url);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		String html = getContent(response);
		writeResponse(html, fileName);
		return html;
	}

	private static void writeResponse(String html, String name) throws IOException {
//		FileUtils.writeStringToFile(new File(name), html);
	}

	private static String getContent(HttpResponse response) throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		String html = result.toString();
		return html;
	}

	private Elements parse(String html, String selector) {
		Document doc = Jsoup.parse(html, "UTF-8");
		return doc.select(selector);
	}
}

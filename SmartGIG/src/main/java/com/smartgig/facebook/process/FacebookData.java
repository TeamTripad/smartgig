package com.smartgig.facebook.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Map;

import com.smartgig.database.connection.MyDBConnection;
import com.smartgig.facebook.dto.AcceptedTokenModel;
import com.smartgig.facebook.dto.FacebookUserLikes;
import com.smartgig.facebook.dto.FacebookUserPosts;
import com.smartgig.facebook.dto.FacebookUserProfile;

//claire*********************************
public class FacebookData {

	public static final String FB_APP_ID = "1647100832211574";
	public static final String FB_APP_SECRET = "94b18d7f72851fe07ed5581669ded644";
	public static final String REDIRECT_URI = "http://localhost:8089/SmartGIG/fbconnect/auth/callback";
	static String accessToken = "";
	static String userID = "";
	Preprocessing processing = new Preprocessing();
	MyDBConnection db = new MyDBConnection();

	public String getFBGraphUrl(String code) {
		String fbGraphUrl = "";
		try {
			fbGraphUrl = "https://graph.facebook.com/oauth/access_token?" + "client_id=" + FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(REDIRECT_URI, "UTF-8") + "&client_secret=" + FB_APP_SECRET + "&code=" + code;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbGraphUrl;
	}

	public String getAccessToken(String code) {
		if ("".equals(accessToken)) {
			URL fbGraphURL;
			try {
				fbGraphURL = new URL(getFBGraphUrl(code));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new RuntimeException("Invalid code received " + e);
			}
			URLConnection fbConnection;
			StringBuffer b = null;
			try {
				fbConnection = fbGraphURL.openConnection();
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(fbConnection.getInputStream()));
				String inputLine;
				b = new StringBuffer();
				while ((inputLine = in.readLine()) != null)
					b.append(inputLine + "\n");
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Unable to connect with Facebook " + e);
			}

			accessToken = b.toString();
			if (accessToken.startsWith("{")) {
				throw new RuntimeException("ERROR: Access Token Invalid: " + accessToken);
			}
		}
		return accessToken;
	}

	public int age(String birthday) {
		String[] value = birthday.split("/");
		// get year from calendar

		// subtract calendar to birthday

		LocalDate today = LocalDate.now();
		LocalDate bday = LocalDate.of(Integer.parseInt(value[2]), Integer.parseInt(value[0]),
				Integer.parseInt(value[1]));

		Period p = Period.between(bday, today);

		return p.getYears();
	}

	public void userDetails(String code) {
		FacebookAPI api = new FacebookAPI(getAccessToken(code));
		System.out.println(getAccessToken(code));
		String graph = api.getFBUserProfile();
		FacebookUserProfile profile = api.getProfileGraphData(graph);
		int age = age(profile.getBirthday());
		profile.setAge(age);
		System.out.println(profile.getFirst_name() + " " + profile.getLast_name() + ": " + profile.getAge());
		// process bio data
		userID = profile.getUser_id();
		db.addUser(profile);
		if (profile.getBio() != null) {
			try {
				String bio = profile.getBio().toString();
				System.out.println(userID);
				processing.processRelevant(bio, userID);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}

	public void userPosts(String code) {
		FacebookAPI api = new FacebookAPI(getAccessToken(code));
		String graph = api.getFBUserPosts();
		@SuppressWarnings("rawtypes")
		ArrayList<Map> posts = api.getPostsGraphData2(graph, userID);

		// for (int i = 0; i < posts.size(); i++) {
		// @SuppressWarnings("unchecked")
		// Map<String, String> fbposts = posts.get(i);
		// System.out.println("id: " + fbposts.get("id"));
		// System.out.println("time: " + fbposts.get("created_time"));
		// if (fbposts.containsKey("story")) {
		// if (fbposts.get("story") != "") {
		//// processing.processRelevant(fbposts.get("story").toString(),
		// userID);
		// System.out.println("story: " + fbposts.get("story").toString());
		// }
		// }
		// if (fbposts.containsKey("message")) {
		// if (fbposts.get("message") != "") {
		//// processing.processRelevant(fbposts.get("message").toString(),
		// userID);
		// System.out.println("message: " + fbposts.get("message").toString());
		// }
		// // message will be preprocessed here
		// }
		// if (fbposts.containsKey("description")) {
		// if (fbposts.get("description") != "") {
		//// processing.processRelevant(fbposts.get("decription").toString(),
		// userID);
		// System.out.println("decription: " +
		// fbposts.get("decription").toString());
		// }
		// // description will be preprocessed here
		// }
		// }
	}

	public void userLikes(String code) {
		FacebookAPI api = new FacebookAPI(getAccessToken(code));
		String graph = api.getFBUserLikes();
		api.getLikesGraphData2(graph, userID);
		// ArrayList<FacebookUserLikes> likelist =
		// api.getLikesGraphData2(graph,userID);
		// process likes

		// for (int i = 0; i < likelist.size(); i++) {
		// if (likelist.get(i).getAbout() != null)
		// processing.processRelevant(likelist.get(i).getAbout(), userID);
		//
		// if (likelist.get(i).getDescription() != null)
		// processing.processRelevant(likelist.get(i).getDescription(), userID);
		//
		// AcceptedTokenModel token = new AcceptedTokenModel("0", userID,
		// likelist.get(i).getName(), 0, 0);
		// if (db.tokenExists(likelist.get(i).getName(), userID)) {
		// AcceptedTokenModel temp = db.getToken(userID, token.getWord()), temp2
		// = new AcceptedTokenModel();
		// int p = temp.getPositiveCount() + token.getPositiveCount(),
		// n = temp.getNegativeCount() + token.getNegativeCount();
		//
		// temp2.setTokenID(temp.getTokenID());
		// temp2.setUserID(temp.getUserID());
		// temp2.setWord(temp.getWord());
		// temp2.setPositiveCount(p);
		// temp2.setNegativeCount(n);
		// db.updateToken(token);
		// } else {
		// db.addToken(token, userID);
		// }
		// }
	}
}

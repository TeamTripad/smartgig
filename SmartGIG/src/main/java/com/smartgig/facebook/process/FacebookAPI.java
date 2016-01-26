package com.smartgig.facebook.process;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smartgig.database.connection.MyDBConnection;
import com.smartgig.facebook.dto.AcceptedTokenModel;
import com.smartgig.facebook.dto.FacebookUserLikes;
import com.smartgig.facebook.dto.FacebookUserPosts;
import com.smartgig.facebook.dto.FacebookUserProfile;

import edu.stanford.nlp.io.EncodingPrintWriter.out;

//claire************************
public class FacebookAPI {
	String accesstoken;
	Preprocessing processing = new Preprocessing();

	public FacebookAPI(String accesstoken) {
		this.accesstoken = accesstoken;
	}

	public String getFBUserProfile() {
		String graph = null;
		try {

			String g = "https://graph.facebook.com/me?fields=id,name,first_name,last_name,"
					+ "birthday,gender,email,bio&" + accesstoken;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
			// System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
	}

	public String getFBUserLikes() {
		String graph = null;
		try {

			String g = "https://graph.facebook.com/me?fields=likes&" + accesstoken;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
			// System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
	}

	public String getFBUserPosts() {
		String graph = null;
		try {

			String g = "https://graph.facebook.com/me?fields=posts&" + accesstoken;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
			// System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
	}

	public String getFBDataNext(String g) {
		String graph = null;
		try {
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
			// System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
	}

	public String getSharedPost(String id) {
		String graph = null;
		try {
			String g = "https://graph.facebook.com/" + id + "?fields=id,description&" + accesstoken;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
			// System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;

	}

	public String getLikeData(String id) {
		String graph = null;
		try {
			String g = "https://graph.facebook.com/" + id + "?fields=id,about,description&" + accesstoken;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
			// System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;

	}

	public FacebookUserProfile getProfileGraphData(String fbGraph) {

		FacebookUserProfile profile = new FacebookUserProfile();
		try {
			JSONObject json = new JSONObject(fbGraph);
			profile.setUser_id(json.getString("id"));
			profile.setFirst_name(json.getString("first_name"));
			profile.setLast_name(json.getString("last_name"));
			if (json.has("birthday"))
				profile.setBirthday(json.getString("birthday"));
			else
				profile.setBirthday(null);

			if (json.has("email"))
				profile.setEmail(json.getString("email"));
			else
				profile.setEmail(null);

			if (json.has("gender"))
				profile.setGender(json.getString("gender"));
			else
				profile.setGender(null);

			if (json.has("bio"))
				profile.setBio(json.getString("bio"));
			else
				profile.setBio(null);
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		return profile;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<FacebookUserPosts> getPostsGraphData(String fbGraph) {
		System.out.println("fbgraph: " + fbGraph.toString());
		ArrayList<FacebookUserPosts> postlist = new ArrayList();
		FacebookUserPosts posts;
		try {

			JSONObject json = new JSONObject(fbGraph);
			JSONObject jsonpost = new JSONObject(json.getString("posts"));
			JSONArray jsonarr = jsonpost.getJSONArray("data");
			System.out.println("json array: " + jsonarr.toString());

			JSONObject jsonpaging = new JSONObject(jsonpost.getString("paging"));

			while (!(jsonarr.isNull(0))) {

				for (int i = 0; i < jsonarr.length(); i++) {
					posts = new FacebookUserPosts();

					JSONObject jsondata = jsonarr.getJSONObject(i);
					posts.setId(jsondata.getString("id"));
					System.out.println("------" + posts.getId());
					if (jsondata.has("story")) {
						posts.setStory(jsondata.getString("story"));
						if (jsondata.getString("story").contains("shared")) {
							String fbGraph2 = getSharedPost(jsondata.getString("id"));
							JSONObject jsonshare = new JSONObject(fbGraph2);
							if (jsonshare.has("description"))
								posts.setDescription(jsonshare.getString("description"));
							else
								posts.setDescription(null);
						}
					} else {
						posts.setStory(null);
					}

					if (jsondata.has("message")) {
						posts.setMessage(jsondata.getString("message"));
					} else {
						posts.setMessage(null);
					}

					postlist.add(posts);
					System.out.println("------added: " + postlist.get(i).getId());

				}

				fbGraph = getFBDataNext(jsonpaging.getString("next"));
				json = new JSONObject(fbGraph);
				jsonarr = json.getJSONArray("data");
				// System.out.println("json array: "+jsonarr.toString());
				if (!(jsonarr.isNull(0))) {
					jsonpaging = new JSONObject(json.getString("paging"));
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		return postlist;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<Map> getPostsGraphData2(String fbGraph, String userID) {
		System.out.println("fbgraph: " + fbGraph.toString());
		ArrayList<Map> postlist = new ArrayList();
		Map fbProfile = new HashMap();

		String message;
		try {

			JSONObject json = new JSONObject(fbGraph);
			JSONObject json2 = new JSONObject(json.getString("posts"));
			JSONArray jsonarr = json2.getJSONArray("data");
			// System.out.println("json array: "+jsonarr.toString());

			JSONObject postpaging = new JSONObject(json2.getString("paging"));

			while (!(jsonarr.isNull(0))) {

				for (int i = 0; i < jsonarr.length(); i++) {
					JSONObject row = jsonarr.getJSONObject(i);
					fbProfile.put("id", row.getString("id"));
					// out.println(row.getString("id"));
					fbProfile.put("created_time", row.get("created_time"));
					if (row.has("story")) {
						fbProfile.put("story", row.getString("story"));
						message = row.getString("story");
						processing.processRelevant(message, userID);
						if (row.getString("story").contains("shared")) {
							String fbGraph2 = getSharedPost(row.getString("id"));
							JSONObject temp = new JSONObject(fbGraph2);
							if (temp.has("description")) {
								fbProfile.put("description", temp.getString("description"));
								message = temp.getString("description");
								processing.processRelevant(message, userID);
							}

						}

					} else {
						fbProfile.put("story", "");
						fbProfile.put("description", "");
					}

					if (row.has("message")) {
						fbProfile.put("message", row.getString("message"));
						message = row.getString("message");
						processing.processRelevant(message, userID);
						// out.println(row.getString("message"));
					} else {
						fbProfile.put("message", "");
					}
					postlist.add(fbProfile);

				}

				fbGraph = getFBDataNext(postpaging.getString("next"));
				json = new JSONObject(fbGraph);
				// json2 = new JSONObject(json.getString("data"));
				jsonarr = json.getJSONArray("data");
				System.out.println("json array: " + jsonarr.toString());
				if (!(jsonarr.isNull(0)))
					postpaging = new JSONObject(json.getString("paging"));
				// fbGraph = getFBUserPostsNext(postpaging.getString("next"));
			}

		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		return postlist;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<FacebookUserLikes> getLikesGraphData(String fbGraph) {
		ArrayList<FacebookUserLikes> likelist = new ArrayList();
		FacebookUserLikes like = new FacebookUserLikes();
		try {
			JSONObject json = new JSONObject(fbGraph);
			JSONObject json2 = new JSONObject(json.getString("likes"));
			JSONArray jsonarr = json2.getJSONArray("data");
			// System.out.println("json array: "+jsonarr.toString());

			JSONObject postpaging = new JSONObject(json2.getString("paging"));

			while (!(jsonarr.isNull(0))) {

				for (int i = 0; i < jsonarr.length(); i++) {
					JSONObject row = jsonarr.getJSONObject(i);
					like.setLikeId(row.getString("id"));
					like.setName(row.getString("name"));

					JSONObject detail = new JSONObject(getLikeData(row.getString("id")));
					if (detail.has("description"))
						like.setDescription(detail.getString("description"));
					else
						like.setDescription(null);

					if (detail.has("about"))
						like.setAbout(detail.getString("about"));
					else
						like.setAbout(null);

					likelist.add(like);

				}
				fbGraph = getFBDataNext(postpaging.getString("next"));
				json = new JSONObject(fbGraph);
				jsonarr = json.getJSONArray("data");
				// System.out.println("json array: "+jsonarr.toString());
				if (!(jsonarr.isNull(0)))
					postpaging = new JSONObject(json.getString("paging"));
			}

		} catch (Exception ex) {
			System.out.println("Error in getting data: " + ex.toString());
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<Map> getLikesGraphData2(String fbGraph, String userID) {
		MyDBConnection db = new MyDBConnection();
		ArrayList<Map> likelist = new ArrayList();
		Map fbProfile = new HashMap();
		String message;
		try {
			JSONObject json = new JSONObject(fbGraph);
			JSONObject json2 = new JSONObject(json.getString("likes"));
			JSONArray jsonarr = json2.getJSONArray("data");
			// System.out.println("json array: "+jsonarr.toString());

			JSONObject postpaging = new JSONObject(json2.getString("paging"));

			while (!(jsonarr.isNull(0))) {

				for (int i = 0; i < jsonarr.length(); i++) {
					JSONObject row = jsonarr.getJSONObject(i);
					fbProfile.put("id", row.getString("id"));
					out.println(row.getString("id"));
					fbProfile.put("name", row.getString("name"));

					JSONObject detail = new JSONObject(getLikeData(row.getString("id")));
					if (detail.has("description")) {
						fbProfile.put("description", detail.getString("description"));
						message = detail.getString("description");
						processing.processRelevant(message, userID);
					}
					if (detail.has("about")) {
						fbProfile.put("about", detail.getString("about"));
						message = detail.getString("about");
						processing.processRelevant(message, userID);
					}

					likelist.add(fbProfile);
					String name = row.getString("name").toString();

					AcceptedTokenModel token = new AcceptedTokenModel("0", userID, name, 1, 0);
					if (db.tokenExists(name, userID)) {
						AcceptedTokenModel temp = db.getToken(userID, name), temp2 = new AcceptedTokenModel();
						int p = temp.getPositiveCount() + token.getPositiveCount(),
								n = temp.getNegativeCount() + token.getNegativeCount();

						temp2.setTokenID(temp.getTokenID());
						temp2.setUserID(temp.getUserID());
						temp2.setWord(temp.getWord());
						temp2.setPositiveCount(p);
						temp2.setNegativeCount(n);
						db.updateToken(temp2);

					} else {
						db.addToken(token, userID);
					}

				}
				if (!(jsonarr.isNull(0)))
					postpaging = new JSONObject(json.getString("paging"));
			}

		} catch (Exception ex) {
			System.out.println("Error in getting data: " + ex.toString());
		}
		return null;
	}

}

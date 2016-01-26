package com.smartgig.facebook.process;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smartgig.facebook.dto.FacebookUserLikes;
import com.smartgig.facebook.dto.FacebookUserPosts;
import com.smartgig.facebook.dto.FacebookUserProfile;

//claire************************
public class FacebookAPI {
	String accesstoken;
	
	public FacebookAPI(String accesstoken) {
		this.accesstoken = accesstoken;
	}

	public String getFBUserProfile() {
		String graph = null;
		try {

			String g = "https://graph.facebook.com/me?fields=id,name,first_name,last_name,"
					+ "birthday,gender,email,bio&"+ accesstoken;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
//			System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
	}
	
	public String getFBUserLikes() {
		String graph = null;
		try {

			String g = "https://graph.facebook.com/me?fields=likes&"+ accesstoken;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
//			System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
	}
	
	public String getFBUserPosts() {
		String graph = null;
		try {

			String g = "https://graph.facebook.com/me?fields=posts&"+ accesstoken;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
//			System.out.println(graph);
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
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
//			System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
	}
	
	
	
	public String getSharedPost(String id){
		String graph = null;
		try {
			String g="https://graph.facebook.com/" + id + "?fields=id,description&" + accesstoken;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
//			System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
		
	}
	
	public String getLikeData(String id){
		String graph = null;
		try {
			String g="https://graph.facebook.com/" + id + "?fields=id,about,description&" + accesstoken;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
//			System.out.println(graph);
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
			if(json.has("birthday"))
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
	public ArrayList <FacebookUserPosts> getPostsGraphData(String fbGraph) {
		System.out.println("fbgraph: "+fbGraph.toString());
		ArrayList <FacebookUserPosts> postlist = new ArrayList();
		FacebookUserPosts posts;
		try {
			
			JSONObject json = new JSONObject(fbGraph);
			JSONObject jsonpost = new JSONObject(json.getString("posts"));
			JSONArray jsonarr = jsonpost.getJSONArray("data");
			System.out.println("json array: "+jsonarr.toString());
			
			JSONObject jsonpaging = new JSONObject(jsonpost.getString("paging"));
			
			while(!(jsonarr.isNull(0))){
				
				posts = new FacebookUserPosts();
				for(int i=0; i<jsonarr.length(); i++){
					JSONObject jsondata = jsonarr.getJSONObject(i);
					posts.setId(jsondata.getString("id"));
					System.out.println("------"+posts.getId());
					if(jsondata.has("story"))
					{
						posts.setStory(jsondata.getString("story"));
						if(jsondata.getString("story").contains("shared")){
							String fbGraph2 = getSharedPost(jsondata.getString("id"));
							JSONObject jsonshare = new JSONObject(fbGraph2);
							if(jsonshare.has("description"))
								posts.setDescription(jsonshare.getString("description"));
							else
								posts.setDescription(null);
						}	
					}else{
						posts.setStory(null);
					}
					
					if(jsondata.has("message"))
					{
						posts.setMessage(jsondata.getString("message"));
					}else{
						posts.setMessage(null);
					}
					postlist.add(posts);
					
				}
				
				fbGraph = getFBDataNext(jsonpaging.getString("next"));
				json = new JSONObject(fbGraph);
				jsonarr = json.getJSONArray("data");
//				System.out.println("json array: "+jsonarr.toString());
				if(!(jsonarr.isNull(0))){
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
	public ArrayList<FacebookUserLikes> getLikesGraphData(String fbGraph){
		ArrayList <FacebookUserLikes> likelist = new ArrayList();
		FacebookUserLikes like = new FacebookUserLikes();
		try{
			JSONObject json = new JSONObject(fbGraph);
			JSONObject json2 = new JSONObject(json.getString("likes"));
			JSONArray jsonarr = json2.getJSONArray("data");
//			System.out.println("json array: "+jsonarr.toString());
			
			JSONObject postpaging = new JSONObject(json2.getString("paging"));
			
			while(!(jsonarr.isNull(0))){
				
				for(int i=0; i<jsonarr.length(); i++){
					JSONObject row = jsonarr.getJSONObject(i);
					like.setLikeId(row.getString("id"));
					like.setName(row.getString("name"));
					
					JSONObject detail = new JSONObject(getLikeData(row.getString("id")));
					if(detail.has("description"))
						like.setDescription(detail.getString("description"));
					else
						like.setDescription(null);
					
					if(detail.has("about"))
						like.setAbout(detail.getString("about"));
					else
						like.setAbout(null);
					
					likelist.add(like);
					
				}
				fbGraph = getFBDataNext(postpaging.getString("next"));
				json = new JSONObject(fbGraph);
				jsonarr = json.getJSONArray("data");
//				System.out.println("json array: "+jsonarr.toString());
				if(!(jsonarr.isNull(0)))
					postpaging = new JSONObject(json.getString("paging"));
			}
			
			
		}catch(Exception ex){
			System.out.println("Error in getting data: "+ex.toString());
		}
		return null;
	}
}

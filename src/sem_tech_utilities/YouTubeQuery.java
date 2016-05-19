package sem_tech_utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class YouTubeQuery {

    private final String USER_AGENT = "Mozilla/5.0";
    private static String videoId = null;
    private String youtube_link = null;
    private String videoID = null;

    public YouTubeQuery(String youtube_link) { 
    	this.youtube_link = youtube_link;
    }
    
    public void extractVideoID(String youtube_link) { 
    	String[] parts = youtube_link.split("v=");
    	this.videoID = parts[1];
    }
    
    public String query() throws Exception {

        String url = "https://www.googleapis.com/youtube/v3/videos?key=AIzaSyAOhXWVCKWrHXkOOPpYv98OdSN2RbVrxPc&part=snippet&id="+this.videoID;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
       
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder builder = new StringBuilder();
        //StringBuffer response = new StringBuffer();

        //parse the json response ++++++++++++++++++++++++++++++++++++++++++++++++
        while ((inputLine = in.readLine()) != null) {
            builder.append(inputLine);
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(builder.toString());
        JSONArray items = (JSONArray) jsonObject.get("items");
        Iterator i = items.iterator();

        String artistAndTitle = null;
        String videoId = null;

        while(i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            if(innerObj.containsKey("snippet")) {
                JSONObject objTitle = (JSONObject) innerObj.get("snippet");
                artistAndTitle = (String) objTitle.get("title");
            }else {
                System.out.print("Not enough information for this video or video not found");
            }
            videoId = (String)innerObj.get("id");
        }

        String artist = "";
        String title = "";
        if(artistAndTitle.matches("(.*)\\s-\\s(.*)")){
            System.out.println("\nNEL PRIMO CASO\n");
            String[] parts = artistAndTitle.split("\\s-\\s");
            artist = parts[0];
            System.out.println("Artist in which I am interested: " + artist);
            title = parts[1];
        }
        else {
            System.out.println("\nNEL SECONDO CASO\n");
            String[] parts = artistAndTitle.split("-\\s");
            artist = parts[0];
            System.out.println("Artist in which I am interested: " + artist);
            title = parts[1];

        }

        //call the DBPediaInterface

        //DBPediaInterface dbPedia = new DBPediaInterface(artist);
        //dbPedia.dbPediaRoutine();

        in.close();
        
        return artist;

    }

}
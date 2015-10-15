/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.artisttracker.control;

import com.github.artisttracker.model.Show;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONArray;


/**
 *
 * @author yurifw
 */
public class FecthShow {
    
    public static List<Show> fetchShows(String artist, String city, String state, String radius){
        try {
            //making request
            InputStream response = null;
            try {
                city = city.replace(" ", "+");
                artist = artist.replace(" ", "%20");
                String url = "http://api.bandsintown.com/artists/"+artist+"/events/search.json?api_version=2.0&app_id=YOUR_APP_ID&location="+city+","+state+"&radius="+radius;
                URL connection = new URL(url);
                response = connection.openStream();
            } catch (FileNotFoundException ex){
                //artist not found, return empty List
                return new ArrayList<>();
            }
            
            //parsing request to json object
            BufferedReader reader = new BufferedReader(new InputStreamReader(response));
            StringBuilder sb = new StringBuilder();
            for (String line = null; (line = reader.readLine()) != null;) {
                sb.append(line).append("\n");
            }            
            JSONArray jsonArray = new JSONArray(sb.toString());
            
            
            //parsing jsob object to Show object
            List<Show> shows = new ArrayList<>();
            for (int i =0; i<jsonArray.length(); i++){
                Show s = new Show();
                s.setBand(jsonArray.getJSONObject(i).getJSONArray("artists").getJSONObject(0).getString("name"));
                s.setCity(jsonArray.getJSONObject(i).getString("formatted_location"));
                s.setDate(jsonArray.getJSONObject(i).getString("formatted_datetime"));
                s.setImageLink(jsonArray.getJSONObject(i).getJSONArray("artists").getJSONObject(0).getString("thumb_url"));
                s.setLocal(jsonArray.getJSONObject(i).getJSONObject("venue").getString("name"));
                s.setTicketStatus(jsonArray.getJSONObject(i).getString("ticket_status"));
                shows.add(s);
            }            
            return shows;
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        
    }
    
}

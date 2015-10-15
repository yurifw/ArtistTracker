/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.artisttracker.model;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author yurifw
 */
public class Show {
    private String band;
    private String city;
    private String local;
    private String ticketStatus;
    private URL imageLink;
    private String date;

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }


    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public URL getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        try {
            this.imageLink = new URL(imageLink);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }   
    

    @Override
    public String toString() {
        String s=this.getBand()+"\n";
        s+=this.getDate()+"\n";
        s+=this.getCity()+", "+this.getLocal()+"\n";
        s+="Ticket status: "+ticketStatus+"\n";
        return s;
    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.artisttracker.main;

import com.github.artisttracker.view.FrmMain;
import javax.swing.JFrame;


/**
 *
 * @author yurifw
 */
public class ArtistTracker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FrmMain frame = new FrmMain();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);
        frame.setTitle("Artist Tracker");
        frame.setVisible(true);
        
    }
    
}

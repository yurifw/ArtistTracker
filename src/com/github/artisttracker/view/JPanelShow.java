/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.artisttracker.view;

import com.github.artisttracker.model.Show;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author yurifw
 */
public class JPanelShow extends JPanel {
    
    private Show show;

    public JPanelShow()  {
        show = new Show();
        show.setBand("Iron Maiden");
        show.setCity("Rio de Janeiro");
        show.setImageLink("https://s3.amazonaws.com/bit-photos/thumb/4047199.jpeg");
        show.setLocal("HSBC Arena");
        show.setTicketStatus("Available");
        show.setDate("Thursday, March 17, 2016 at 7:00PM");
        
        buildGUI();
    }

    
    public JPanelShow(Show show){
        this.show = show;
        buildGUI();
    }
    
    private void buildGUI(){
        try {
            
            this.setLayout(new FlowLayout(FlowLayout.LEADING));
            //reading and resizing image from link
            BufferedImage image = ImageIO.read(show.getImageLink());
            Image buffImg = image.getScaledInstance(130, 130,Image.SCALE_SMOOTH);
            JLabel lblImage = new JLabel(new ImageIcon(buffImg));            
            this.add(lblImage);
            
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));            
            infoPanel.add(new JLabel(show.getBand()));
            infoPanel.add(new JLabel(show.getDate()));
            infoPanel.add(new JLabel(show.getCity()+", "+show.getLocal()));
            infoPanel.add(new JLabel("Ticket status: "+show.getTicketStatus()));
            this.add(infoPanel);
            
        } catch (IOException ex) {
            Logger.getLogger(JPanelShow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

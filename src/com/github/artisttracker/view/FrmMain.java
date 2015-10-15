/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.artisttracker.view;

import com.github.artisttracker.control.FecthShow;
import com.github.artisttracker.model.Show;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

/**
 *
 * @author yurifw
 */
public class FrmMain extends JFrame {

    private JTextField txtFolder = new JTextField();
    private JTextField txtCity = new JTextField();
    private JTextField txtState = new JTextField();
    private JTextField txtDistanceRadius = new JTextField();
    private JButton btnRefresh = new JButton("Refresh");
    private final JPanel showsPanel = new JPanel();

    public FrmMain() {
        buildGUI(); 
        addListeners();
    }

    private void buildGUI() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JScrollPane scrollPane = new JScrollPane(showsPanel);
        showsPanel.setLayout(new BoxLayout(showsPanel, BoxLayout.PAGE_AXIS));

        JPanel configurationPanel = new JPanel();
        configurationPanel.setLayout(new BoxLayout(configurationPanel, BoxLayout.PAGE_AXIS));
        JLabel lblFolder = new JLabel("Folder");
        configurationPanel.add(lblFolder);
        txtFolder.setMaximumSize(new Dimension(3000, 30));
        configurationPanel.add(txtFolder);
        JLabel lblCity = new JLabel("Your City");
        configurationPanel.add(lblCity);
        txtCity.setMaximumSize(new Dimension(3000, 30));
        configurationPanel.add(txtCity);
        JLabel lblState = new JLabel("Your State (abbreviation)");
        configurationPanel.add(lblState);
        txtState.setMaximumSize(new Dimension(3000, 30));
        configurationPanel.add(txtState);
        btnRefresh.setMaximumSize(new Dimension(3000, 30));
        JLabel lblDistanceRadius = new JLabel("Distance Radius (maximum 150 miles)");
        configurationPanel.add(lblDistanceRadius);
        txtDistanceRadius.setMaximumSize(new Dimension(3000, 30));
        configurationPanel.add(txtDistanceRadius);
        configurationPanel.add(btnRefresh);

        scrollPane.setMinimumSize(new Dimension(600, 9999));
        splitPane.setLeftComponent(scrollPane);
        splitPane.setRightComponent(configurationPanel);
        this.add(splitPane);
    }

    private void addListeners() {
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showsPanel.removeAll();
                
                for (String band : scanFolder(txtFolder.getText())){
                    for (Show show : FecthShow.fetchShows(band, txtCity.getText(), txtState.getText(), txtDistanceRadius.getText())){
                        showsPanel.add(new JPanelShow(show));
                    }
                }

                showsPanel.revalidate();
                showsPanel.repaint();
            }
        });
    }

    public String[] scanFolder(String folder) {
        File file = new File(folder);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        return directories;
    }
    
}

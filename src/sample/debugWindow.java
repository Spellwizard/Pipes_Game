package sample;


import java.awt.*;
import javax.swing.*;


import java.awt.Color;
import java.awt.Graphics;

//This is used to track keyboard inputs
import java.util.ArrayList;

public class debugWindow extends JFrame{

    private int WindowWidth = 700;
    private int WindowLength = 300;


    JPanel Pan_Debug = new JPanel();

    private ArrayList<String> debugLines = new ArrayList<String>();

    private String debugInfo ="----------------\n\nInitializing Debug System\n\n--------------------";

    private String debugTitle ="----------------\n\nDebug System\n\n--------------------";

    public debugWindow(){

    }

    public void init(){
        this.setTitle("Debug Window");
        this.setVisible(true);
        this.setSize(WindowWidth,WindowLength);
        repaint();
    }
    //used on making a new sheet class object
    public void updateText(String info){
        debugInfo = info;
        debugLines.add(debugInfo);
        repaint();
    }



    public void paint(Graphics g) {
        super.paint(g);

        // clear the screen of all elements
        //super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0,0,WindowWidth, WindowLength);
        g.setColor(Color.white);

        int counter = 85;

        g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        g.drawString(debugTitle, 15, 50);
        for(int i = debugLines.size()-1; i>0;i--) {
            g.drawString(debugLines.get(i), 15, counter);
            counter+=13;
        }
        System.out.println("\n--------------------------\n"+debugInfo);

    }





}
package sample;


import javax.swing.*;

//this program was to be a simple pipe game that you connected a path from the left to the right of the screen

//it does have a simple menu with a few simple buttons

//the game currently runs but doesn't correctly find a path but does allow using a mousse click to rotate and highlight a tile.

//idk maybe ill circle back later after 6/25/19 8:55pm cst dbq iowa

//it has a number of disabed featues such as a debug screen for when the program is converted in an exe file to figure out what is going on.

public class Main{

    public static void main(String[]args){
        System.out.println("Starting Main");
        //make the class and start the initial sheet construction needed
        Menu frame = new Menu("Game Menu");


        //make sure the window will stop program on closing window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //make the window visible
        frame.setVisible(true);
    }



}



package sample;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//This is used to track keyboard inputs
import java.awt.event.KeyListener;

import java.awt.Color;
import java.awt.Graphics;

public class Menu extends JFrame implements ActionListener {

    JPanel Pan_Menu = new JPanel();



    KeyListener InputTracker = new KeyListener() {


        @Override
        public void keyPressed(KeyEvent e) {
            // for quick testing and use of find each key code on the fly
            //System.out.println(e.getKeyCode()+" ");

            //Player movements for W,A,S,D
            // and make sure that such a move won't move the player off the map

            //Check if key 'W' is pressed to move character up

            System.out.println("KeyPressed: "+e.getKeyCode());
            repaint();
        }
        //these two are needed but will be unused
        @Override
        public void keyTyped(KeyEvent e){}
        @Override
        public void keyReleased(KeyEvent e) {}

    };

    @Override
    public void paint(Graphics g){

    }


    public Menu(String windowName){
        System.out.println("New Sheet made: "+windowName);
        this.setName(windowName);



        this.init();


    }

    //used on making a new sheet class object
    private void init(){
        this.setBackground(Color.BLACK);
        this.setSize(600,600);

        //Add a focus on the window to track for keyboard inputs
        this.addKeyListener(InputTracker);



        MainMenu();


        repaint();

    }

    //called when the mainmenu needs to be drawn
    //it should clear the window and set the mainmenu up with its buttons and items

    private void MainMenu(){
        System.out.println("MainMenu Function running");

        //the screen MUST be emptied
        //somehow



        //make a new container to have the primary buttons
        Container MenuButtonsContainer = new Container();


        int ButtonXPos = 100;

        int ButtonWidth = 400;

        int ButtonLength = 100;


        //Primary buttons

        //quit button the close the window
        //make the quit button
        Button BQuit = new Button("Quit Program");
        //set a code to check when any buttons are clicked
        BQuit.setActionCommand("BQuit");
        BQuit.addActionListener(this);
        BQuit.setVisible(true);
        BQuit.setBackground(Color.RED);

        BQuit.setBounds(ButtonXPos,450, ButtonWidth, ButtonLength);

        MenuButtonsContainer.add(BQuit);


        //Start Game button 'Play'
        //set a code to check when any buttons are clicked
        Button SGame = new Button("SGame");
        //set a code to check when any buttons are clicked
        SGame.setActionCommand("SGame");
        SGame.addActionListener(this);
        SGame.setVisible(true);
        SGame.setBackground(Color.GREEN);

        SGame.setBounds(ButtonXPos,50, ButtonWidth, ButtonLength);

        MenuButtonsContainer.add(SGame);

        //Direct Connect
        //This is to establish a direct connection to another computer for the purposes of 2 player either for coop or pvp
        //for minimum usable purpoes this won't be properly implemented
        Button Bconnect = new Button("Direct Connect");

        Bconnect.setActionCommand("Bconnect");
        Bconnect.setVisible(true);
        Bconnect.setBackground(Color.WHITE); // set the background color of the button
       // Bconnect.setForeground(Color.WHITE); // set the text color of the button
        Bconnect.setBounds(ButtonXPos, 150, ButtonWidth, ButtonLength);
        MenuButtonsContainer.add(Bconnect);

        //add the input for the text box to input the IP of another computer to connect to
        TextArea IPcode = new TextArea();
        IPcode.setVisible(true);
        IPcode.setSize(3,3);
        IPcode.setBounds(ButtonXPos, 250, ButtonWidth, ButtonLength/2);

        MenuButtonsContainer.add(IPcode);


        //add all the buttons to the Container


        //Make sure the container will be visible
        MenuButtonsContainer.setVisible(true);

        MenuButtonsContainer.setForeground(Color.black);

        this.add(MenuButtonsContainer);
        repaint();
    }

    //this can be used to check if a button, check box or the like is used
    public void actionPerformed(ActionEvent ae){
        String action = ae.getActionCommand();
        System.out.println("ActionPerformed "+action);

        //check to see what button was pressed and execute relevant actions
        if(action.equals("BQuit")){
            System.exit(0);
        }
        //start the game
        else if(action.equals("SGame")){
            //Open the new game window
            PipeGame01 newGameWindow = new PipeGame01("Game Window");
            //newGameWindow.add(new PixelCanvas());
            newGameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //make the window visible
            newGameWindow.setVisible(true);
            System.out.println("The PVPGame window should now be open");
            this.setVisible(false); // hides the menu window
        }
    }




}
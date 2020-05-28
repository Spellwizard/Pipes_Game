package sample;




import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//This is used to have dynamic array's of objects
import java.util.ArrayList;
//this allows easy calling to make random numbers
import java.util.Random;

public class PipeGame01 extends JFrame implements ActionListener {


    private int WindowWidth = 700; // used to set the initial size of the width of he window the changes to the width
    private int WindowLength = 800; // used to see the initial size of the length of the window and the changes to the window width


    //col & row are used to track the columns and rows of the board needed
    int col = 10; // the number of columns that will be made

    int row = 8; // the number of rows that will be made

    int selectedRow = -1;
    int selctedCol = -1;

    int Xoffset = 11;
    int Yoffset = 35;


    Random random = new Random();



    //create aa blank array to track the tiles on the board
    private ArrayList<PipeTile> aTiles = new ArrayList<PipeTile>();


    private int rowSize = WindowWidth / row; // used to track the size of the rows
    private int colSize = WindowWidth / row; // used to track the size of the rows

        MouseListener ratListner = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                    //System.out.println("Player Click: "+ getMouseRow(e.getX())  + ", "+ getMouseCol(e.getY()));
                    selctedCol= getMouseCol(e.getY());
                    selectedRow = getMouseRow(e.getX());
                    repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };




    public debugWindow debugWindow = new debugWindow();

    /**
     * @calls WindowWidth
     *
     * @param mouseX the actual location of the mouse
     * @return the row the mouse is in
     */

    public int getMouseRow(int mouseX){
        int num_row = 0;
            //loop through each row until the relevant row is found and return that row
        //due to the window bar taking some of the window space the X value needs to be offset to compensate
        for(int X = Xoffset; X < WindowWidth; X+=rowSize) {
            //check the mouse location to be less than the row + the row size
            if( (X + rowSize) >mouseX) {
                return num_row;
            }
            num_row++;
        }
        return 0; // failsafe reuturn if error;
    }

    /**
     * @calls WindowLength
     *
     * @param mouseY the actual location of the mouse
     * @return the column the mouse is in
     */

    public int getMouseCol(int mouseY){
        int num_col = 0;
        //loop through each row until the relevant row is found and return that row
        //due to the window bar taking some of the window space the X value needs to be offset to compensate
        for(int Y = Yoffset; Y < WindowLength; Y+=colSize) {
            //check the mouse location to be less than the row + the row size
            if( (Y + colSize) >mouseY) {
                return num_col;
            }
            num_col++;
        }
        return 0; // failsafe reuturn if error;
    }




    //very simple program to find if there is a straight line from the left side of the board to the right

    //give intereger currentCol find any connects to the targetcolumn and return a true / false statement
    //and update those tiles to be fillec
    public boolean simpleStraightRowConnection(int currentCol, int targetCol){

        //grab a usable updated sorted array to use to simplify coding process
        int[][][] SortedTiles = this.sortedArray(aTiles);

        //loop through the current column and set all the connected tiles to filled

        for(int iRow = 0; iRow<row; iRow++) {
            try {



                PipeTile currentTile = (aTiles.get(SortedTiles[iRow][currentCol][0]));
                PipeTile targetTile = (aTiles.get(SortedTiles[iRow][targetCol][0]));


                System.out.println("-----------------\nNew pair to compare\n"+currentTile.getRow()+", "+currentTile.getCol()+" values: isUP: "+currentTile.isUp()
                        + " isRight: "+currentTile.isRight() +" isDown: "+currentTile.isDown()+" isLeft: "+currentTile.isLeft()
                );
                System.out.println(targetTile.getRow()+", "+targetTile.getCol()+" values: isUP: "+targetTile.isUp()
                        + " isRight: "+targetTile.isRight() +" isDown: "+targetTile.isDown()+" isLeft: "+targetTile.isLeft()
                );



            //check if the current iterated row current column is onnected to target column
            if(currentTile.isConnected(targetTile)  ){

                        //now set both tiles to filled

                currentTile.setFull(true);
                targetTile.setFull(true);




                System.out.println("Something is connected(");

            }

            } catch (Exception e) {
                System.out.println("simpeStraightRowConnection Error - iRow:" + iRow + " , " + e.toString());
            }

            System.out.println("\n-------------------------------------------\n\n");


        }




     return false;
    }




    public PipeGame01(String windowName){

        System.out.println("New Sheet made: "+windowName);

        this.setTitle(windowName);


        this.init(); // start some of the one time items up


        repaint();

    }

    //set up all the initalized menu bar stuff
    private void menuBarInit(){


        setLocationRelativeTo(null);

        var menuBar = new JMenuBar();

        var fileMenu = new JMenu("Options");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        var eMenuItem = new JMenuItem("Open Debug Screen");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener((event) -> makeDebug());

        fileMenu.add(eMenuItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        repaint();
    }

    private void makeDebug(){
        debugWindow.init();
        debugWindow.updateText("\n------------------\n\nStarting Debug\n\n------------------------");
    }

    //used on making a new sheet class object
    private void init()
    {
        setTitle("PipeGame01"); // name the window
        setSize(WindowWidth, WindowLength); // set the size of the window


        this.addMouseListener( ratListner);
       // this.menuBarInit(); // the menubar that allows access to the debug window
        PipesInit();
        makePipePath02();

        repaint();


    }

    //Populate the needed array of Tiles
    private void PipesInit(){
        int counter = 0;

        for(int r = 0; r < row; r++) {

            for (int c = 0; c < col; c++) {

                int randomInteger = random.nextInt(10);
                int randomNums = random.nextInt(10);

                aTiles.add(new PipeTile(true, true, true, true, Color.black, Color.green, Color.blue, r,c));

                if(randomInteger >5){

                    aTiles.get(counter).setUp(false);
                }
                if(randomNums <5){
                    aTiles.get(counter).setDown(false);
                }
                if(randomInteger%2 == 0 || randomNums > 7){
                    aTiles.get(counter).setRight(false);
                }
                if(randomNums%2 != 0  || randomInteger <3){
                    aTiles.get(counter).setRight(false);
                }

                counter++;
            }
        }



    }

    //make a usable path from the left to the right side and then randomly rotate the map
    private void makePipePath02() {

        System.out.println("makePipePath 02");

        //make a path starting at the 0 row and a random col
        //randomly pick a direction that doensn't exceed the col / rows and increase the chance of moving to the right

        //stop looping if the path takes the entire map and doensn't stop somehow

        int pathRow = 0; //start the path on the left side of the screen
        int pathCol = random.nextInt(col); // start on any applicable column

        boolean moveUp = false;
        boolean moveDown = false;
        boolean moveRight = false;


        for (int r = pathRow; r - 1 < row; ) {
            //reset the values for a new loop
            moveUp = false;
            moveDown = false;
            moveRight = false;

            //make a random number
            int movement = random.nextInt(3);


            //move the tile to the right if the value is 0
            if (movement == 0) {
                moveRight = true;
                r++;
            }
            //move the path UP
            else if (movement == 1) {
                moveUp = true;
            }
            //move the path down
            else if (movement > 1) {
                moveDown = true;
            }

            //safety catch to stop the path leaving the board
            //stop the path leaving the top of the board
            if (pathCol + 1 > col + 1 && moveDown) {
                pathCol -= 2; // bounce the path back upwards the playable area to help reduce looping
                moveDown = false;
                moveUp = true;
            }
            //stop the path going off the top of the map
            else if (moveUp && pathCol - 1 < 0) {
                pathCol += 2;// bounce the back downwards
                moveUp = false;
                moveDown = true;
            }

            //if the path moved right then go back to the left tile and set that isRigh value to true
            //then set the current tile position isLeft position true
            int tasks = 2; // if this reaches 0 then end the loop
            if (moveRight) {
                for (PipeTile a : aTiles) {
                    //go back to the left tile and set that isRigh value to true
                    if (a.getRow() == r - 1 && a.getCol() == pathCol) {
                        a.setRight(true);
                        tasks--;
                        //a.setFull(true); //color the pipe so that it is visible
                    }
                    //then set the current tile position isLeft position true
                    if (a.getRow() == r && a.getCol() == pathCol) {
                        a.resetConnections();
                        a.setLeft(true);
                        tasks--;
                        //a.setFull(true); //color the pipe so that it is visible
                    }
                    if (tasks == 0) {
                        break;
                    }
                }

            }


            //if the path moved up then
            // the lower tile has to be updated to move up true
            // the new location has to connect to the lower spot

            if (moveUp) {
                tasks = 2; // if this reaches 0 then end the loop

                for (PipeTile a : aTiles) {
                    // the lower tile has to be updated to move up true
                    if (a.getRow() == r && a.getCol() == pathCol + 1) {
                        a.setUp(true);
                        tasks--;
                       // a.setFull(true); //color the pipe so that it is visible
                    }
                    //then set the current tile position isLeft position true
                    if (a.getRow() == r && a.getCol() == pathCol) {
                        a.resetConnections();
                        a.setDown(true);
                        tasks--;
                        //a.setFull(true); //color the pipe so that it is visible
                    }
                    if (tasks == 0) {
                        break;
                    }
                }
            }


            // if the path moved down then
            //the upper tile needs to be set to isdown true
            // the current location needs to be set isup true
            if (moveDown) {
                tasks = 2; // if this reaches 0 then end the loop

                for (PipeTile a : aTiles) {


                //the upper tile needs to be set to isdown true
                if (a.getRow() == r && a.getCol() == pathCol - 1) {
                    a.setDown(true);
                    tasks--;
                   // a.setFull(true); //color the pipe so that it is visible
                }
                // the current location needs to be set isup true
                if (a.getRow() == r && a.getCol() == pathCol) {
                    a.resetConnections();
                    a.setUp(true);
                    tasks--;
                   // a.setFull(true); //color the pipe so that it is visible
                }
                if (tasks == 0) {
                    break;
                }
            }
        }
    }
        //randomly rotate the each tile up to a full circle
        for (PipeTile a : aTiles) {
           a.rotateClockwise(random.nextInt(4));//randomly rotate the tile up to a full circle
            a.setFullPipe(Color.pink);
        }


    }


    //make a usable path from the left to the right side and then randomly rotate the map
    private void makePipePath(){

        System.out.println("makePipePath 01");

        //make a path starting at the 0 row and a random col
        //randomly pick a direction that doens't exceed the col / rows and increase the chance of moving to the right

        //stop looping if the path takes the entire map and doens't stop somehow

        int pathRow = 0;
        int pathCol = random.nextInt(col);

        //System.out.println("makePipePath starting column: "+pathCol+", run size: "+col*row);

        for(int i = 0;i < (col*row); i++) {

            boolean moveUp = false;
            boolean moveDown = false;
            boolean moveRight = false;

            int moveIndication = random.nextInt(100);

            if (moveIndication > 67) { // 50% to move the path right
                moveRight = true;
                if (pathRow == row) {
                    break;//path from left to right ocmplete
                }
            } else if (moveIndication < 68 && moveIndication > 25) {
                moveUp = true;

            } else if (moveIndication < 26 && moveIndication > -1) {
                moveDown = true;
            }

            //stop any in - action movement
            if(moveUp ==moveDown == moveRight == false){
                moveRight = true;
                System.out.println("Inaction detcted in pathmaking: adjusted to move right");
            }


            //actually change the values of the selected tile
            System.out.println("Move Up? "+moveUp+" Move Down? "+moveDown+" Move Right? "+moveRight +" moveIndication: "+moveIndication);
            for(PipeTile Tile: aTiles) {

                //make sure bounds are not exceeded and move the path up 1 column
                if (Tile.getCol() == pathCol && Tile.getCol() == pathCol) {

                    if(moveUp&&(pathCol - 0  )< 1 ){
                        pathCol++;
                        Tile.setUp(true);
                        Tile.setDown(true);
                    }

                    //move the path down
                    else if(moveDown&& pathCol -1 < col){
                        pathCol--;
                        Tile.setDown(true);
                        Tile.setUp(true);
                    }

                    //move the path right

                    else if(moveRight ){
                        pathRow++;
                        Tile.setRight(true);
                        Tile.setLeft(true);
                    }

                    Tile.setFull(true);
                    System.out.println("The path is: "+pathRow+", "+pathCol);
                }
            }
        }

        //randomly rotate the each tile up to a full circle
        for(PipeTile a: aTiles){
            a.rotateClockwise(random.nextInt(4));//randomly rotate the tile up to a full cirlce
        }

    }


    public void paint(Graphics g) {
        super.paint(g); //clear the page




        //draw the basic checkerboard with black and blue
       // this.drawCheckerdBoard(g,Color.blue, Color.pink);

        //call the function that draws all the pipes
        this.drawPipeBoard(g);

        drawSelTile02(g);

    }


    private void fillPipeBoard() {
            //from the players selected tile check to see if the tile connects to the 0 row
          boolean  isConnectedLeft;
            boolean isConnectedRight;

            int[][] pathCoords = new int[100][0];

            int[][][] tempArray = sortedArray(aTiles);


            try {
                //loop from the player col & Row to the limit of the row to find what is connected and if at any point a connection is not made then to break
                for (int colY = selctedCol; colY < col; colY++) { // start at the player selected col and loop down
                    //check to see if the current box connects to the box downwards

                    PipeTile currentBox = aTiles.get(tempArray[selectedRow][colY][0]);
                    PipeTile belowBox = aTiles.get(tempArray[selectedRow][colY - 1][0]);

                    //check the current box is connected to the lower box
                    if (currentBox.isConnected( belowBox)) {
                        //add the current position to the path coordinates
                    }

                }
            }
            catch(Exception e){
                System.out.println("PipeGame01.fillPipeBoard - Error in finding any applicable paths "+e.toString());

            }

        /***
         * Loop through a given position to find all connected positions in the column
         * check the upper / lower values in the column against the column to the left
         * use the given y values that made a connection to the right to use the y - values to use the above lines to check those values to find any lines connected to the left of thoose columns until the column is 0 or values aren't connected
         */



    }


    //Return an array in a 2d grid with an additional 0 deep array to track that position in the overall open list
    private int[][][] sortedArray(ArrayList<PipeTile> alpha){

            int[][][] tempArray = new int[row][col][1];

           // System.out.println("\n Row: "+ row+" , Column: "+ col);

            try {

                for (PipeTile a : aTiles) {

                    tempArray

                            [a.getRow()]

                            [a.getCol()]

                            [0]

                            = aTiles.indexOf(a);
                }

            }
            catch(Exception e){
                System.out.println("\n\nError correctly populating sortedArray in PipeGame01 "+e.toString()+"\n\n");
            }

        return tempArray; // error response
    }

    //for motivational purposes:
    //find if the current  player selected col reaches the 0th col
    //@return the arraylist of the path from the player selected col back to the 0th column
    private ArrayList<PipeTile> basicLateralPath(int desiredColumn){


        //catch if the desirecolumn is exceeding logic, ie smaller than 0 or greater than the total number of columns
        if(desiredColumn < -1 || desiredColumn > col){
            return null; // used as an error return
        }

        //make an empty array list to track the path to the desiredcolumn

        ArrayList<PipeTile> path = new ArrayList<>();

        //determine if the desiredcolumn is greater than the playeerselected column
        if(desiredColumn > selctedCol){

            //loop through from the player selected column to the right until the desired column is caught


        }

        //if the desiredcolumn is lesser than the player selected column
        if(desiredColumn < selctedCol){

        }

        return null;
    }



    public void resetTilesFilled(){

        for(PipeTile a: aTiles){
            a.setFull(false);
        }
    }

    //draw the player selected position
    private void drawSelTile02(Graphics g){


        //System.out.println("Player Selected Grid: "+selectedRow+", "+selctedCol);
        //draw the player selected spot


        for(PipeTile Tile: aTiles){



            if(Tile.getRow()==selectedRow&&Tile.getCol()==selctedCol){
                Color tBackground = Tile.getTileBack();//temporarily save the current background color
                Color PipeColor = Color.white;
                Tile.rotateClockwise();
                if(Tile.isFull()){
                    PipeColor = Tile.getFullPipe();
                    Tile.setFullPipe(Color.RED);

                    Tile.setTileBack(Color.white); //change the background color to a highlighted color
                    Tile.drawTile(g, (Tile.getRow()*rowSize)+Xoffset, Yoffset+(Tile.getCol()*colSize), rowSize, colSize);
                    Tile.setFullPipe(PipeColor);
                }
                else{
                    PipeColor = Tile.getEmptyPipe();
                    Tile.setEmptyPipe(Color.red);

                    Tile.setTileBack(Color.white); //change the background color to a highlighted color
                    Tile.drawTile(g, (Tile.getRow()*rowSize)+Xoffset, Yoffset+(Tile.getCol()*colSize), rowSize, colSize);

                    Tile.setEmptyPipe(PipeColor);
                }
                //System.out.println(Tile.getRow()+", "+Tile.getCol()+" values: isUP: "+Tile.isUp()
                //        + " isRight: "+Tile.isRight() +" isDown: "+Tile.isDown()+" isLeft: "+Tile.isLeft()
                //);


                Tile.setTileBack(tBackground);// reset the background color
            }
        }

    }

    private void drawSelectedTile(Graphics g)
    {

        //draw the player selected spot


        for(PipeTile Tile: aTiles){

            if(Tile.getRow()==selectedRow&&Tile.getCol()==selctedCol){
                Color tBackground = Tile.getTileBack();//temporarily save the current background color
                Color PipeColor = Color.white;
                Tile.rotateClockwise();
                if(Tile.isFull()){
                    PipeColor = Tile.getFullPipe();
                    Tile.setFullPipe(Color.RED);

                    Tile.setTileBack(Color.white); //change the background color to a highlighted color
                    Tile.drawTile(g,(selectedRow*rowSize)-Xoffset, (selctedCol*colSize), rowSize, colSize);
                    Tile.setFullPipe(PipeColor);
                }
                else{
                    PipeColor = Tile.getEmptyPipe();
                    Tile.setEmptyPipe(Color.red);

                    Tile.setTileBack(Color.white); //change the background color to a highlighted color
                    Tile.drawTile(g,Tile.getRow()*rowSize, Tile.getCol()*colSize, rowSize, colSize);

                    Tile.setEmptyPipe(PipeColor);
                }


                Tile.setTileBack(tBackground);// reset the background color
            }
        }



    }


    private void drawPipeBoard(Graphics g){
        //update the window size values
        WindowWidth=this.getWidth();
        WindowLength = this.getHeight();

        //make a checkerboard pattern on the screen equally sized based on the window size

        colSize = WindowLength / col; // calculate the actual size of the rows

        rowSize = WindowWidth / row; // calculates the actual size of the columns
        for(PipeTile Tile: aTiles){
            Tile.drawTile(g, (Tile.getRow()*rowSize)+Xoffset, Yoffset+(Tile.getCol()*colSize), rowSize, colSize);
        }

    }



    /**
     *The class values of the col, row are used to distiished how many rows / columns are needed
     * The  WindowLength and WindowWidth are used to disginquished how many columns may be neded to fill the vailable space
     *
     * This function should only be called by the paint class as anyone else may cause an endless loop
     *
     * @param g ised for which graphics to draw on
     * @param a used as the first color to draw the board with
     * @param b used as the second color to draw the board with
     */
    private void drawCheckerdBoard(Graphics g, Color a, Color b)
    {
        //update the window size values
        WindowWidth=this.getWidth();
        WindowLength = this.getHeight();

        //make a checkerboard pattern on the screen equally sized based on the window size

        colSize = WindowLength / col; // calculate the actual size of the rows

        rowSize = WindowWidth / row; // calculates the actual size of the columns

        try{
            debugWindow.updateText("\nRedrawing Rectangles at Sizes: "+ rowSize +", "+ colSize+"\n");
        }
        catch(Exception e){
            System.out.println(e.toString());
        }

        boolean isWhite = false; // used to change back and forth the value of squares color

        //loop through every row
        // within each row loop through every column
        //in each position make a box in the available space

        for(int r = 0; r < row; r++){

            for(int c = 0; c< col; c++){
                //make a square at the current iterated position of the columns and rows of the size of those columns and rows divided by the window size
                int X = r * rowSize + Xoffset; // calculate the position of the top left of the rectangle
                int Y = c * colSize + Yoffset;




                //alternate what needs to be drawn where
                if(isWhite){
                    g.setColor(Color.blue);
                    g.fillRect(X,Y,rowSize,colSize);//make a square at the current iterated position of the columns and rows of the size of those columns and rows divided by the window size
                    //flip it back to the other color
                    isWhite = !isWhite;
                }
                else{
                    g.setColor(Color.GREEN);
                    g.fillRect(X,Y,rowSize,colSize);//make a square at the current iterated position of the columns and rows of the size of those columns and rows divided by the window size
                    //flip it back to the other color
                    isWhite = !isWhite;
                }


            }

            //end of a row looping through each column position

            if(col==row &&(col%2)==0){
                isWhite = !isWhite;
            }
            else if(row<col && (col%2)==0){
                isWhite = !isWhite;
            }
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}





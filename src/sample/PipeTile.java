package sample;

import java.awt.*;

public class PipeTile {
    /**
     * This class is built to track and provide the modifable drawing of pipe tiles
     * This program needs be to able to track which kind of pipe tile it is,
     * allow for changes to the tile
     * have a respective draw function given a graphics to draw on, and track which direction the pipe can go
     */

    //These 4 functions define which direction the pipe can travel in
    private boolean isUp;
    private boolean isDown;
    private boolean isLeft;
    private boolean isRight;

    //This function tracks what the background color of the pipe tile is
    private Color TileBack;

    //This function defines if the pipe is filled or not
    private boolean isFull;

    //This tracks the color of the basic nonfilled pipe
    private Color emptyPipe;
    //this color is a filled pipe
    private Color fullPipe;

    //track the position of the tile using the provided columns & rows system
    private int row;
    private int col;

    //track the % of the pipe to the size of the tile
    private int pipeSize = 33;

    /**
     * @param goesUp sets if the tile accepts movement upwards
     * @param goesDown sets if the tile accepts movemnt downards
     * @param goesLeft sets if the tile accepts movement left
     * @param goesRight sets if the tile accepts movement right
     * @param BackgroundColor sets the background color of the tile
     * @param emptyPipe sets the color of the empty pipe
     * @param fullPipe sets the color of a full pipe
     * @param Posrow tracks the row position of the tile
     * @param Poscol tracks the col position of the tile
     */
    public PipeTile(boolean goesUp, boolean goesDown,
                    boolean goesLeft, boolean goesRight,
                    Color BackgroundColor, Color emptyPipe, Color fullPipe, int Posrow, int Poscol
    ){
            isUp = goesUp;
            isDown = goesDown;
            isLeft = goesLeft;
            isRight = goesRight;
            TileBack = BackgroundColor;
        this.emptyPipe = emptyPipe;
        this.fullPipe = fullPipe;
        row = Posrow;
            col = Poscol;

            isFull = false;
    }


    public void drawTile(Graphics g, int X, int Y, int Xsize, int Ysize){

        //draw the background of the tile
        g.setColor(TileBack);
        g.fillRect(X,Y, Xsize, Ysize);

        //change the pipe color based on if the pipe if full or not
        if(isFull){
            g.setColor(fullPipe);
        }
        else{
            g.setColor(emptyPipe);
        }




        //if the tile is accesable via up direction then draw an upwards pipe to the center of the tile
        if(isUp){
            //make a rectanngle @ X + (the width of the tile divided by the % size of pipe)
            //make the Y position start at the top of the tile
            //set the width to the width of the tile divided by the % size of the pipe
            //finally draw the tile down to half way of the tile
          g.fillRect((
                  (Xsize *pipeSize)/100)+X,
                  Y,
                  ((Xsize *pipeSize)/100),
                  Ysize /2);
        }
        if(isDown){
            //uses the same logic as drawing is up but
            //the down starts at the halfway position
            g.fillRect(X + ((Xsize * pipeSize)/100), (Ysize/2)+Y, (Xsize * pipeSize)/100, Ysize /2);
        }

        if(isLeft){
            g.fillRect(X, Y + ((Ysize * pipeSize)/100), Xsize /2 , (Ysize * pipeSize)/100);
        }

        if(isRight){
            g.fillRect(X+(Xsize/2), Y + ((Ysize * pipeSize)/100), Xsize /2 , (Ysize * pipeSize)/100);
        }

    }


    //change the values of the given directions in a clockwise direction
    public void rotateClockwise(){
       boolean left = isLeft;
       boolean up = isUp;
       boolean down = isDown;
       boolean right = isRight;

       isUp = left;
       isRight = up;
       isDown = right;
       isLeft = down;
    }


    //use recursive logic to rotate clockwise to the amount requested
    public int rotateClockwise(int r) {
        if (r == 0) {
            return 0;
        } else {
            rotateClockwise(r -= 1);
            rotateClockwise();
            return 0;
        }

    }

    /**
     * clear all existing directions
     */
    public void resetConnections(){
        isLeft = false;
        isRight = false;
        isUp = false;
        isDown = false;
    }

    //basic modifers or 'setters' for the values of the tile
    public void setUp(boolean Up){
        isUp = Up;
    }
    public void setDown(boolean down){
        isDown = down;
    }
    public void setLeft(boolean left){
        isLeft = left;
    }
    public void setRight(boolean right){
        isRight = right;
    }

    public void setRow(int r){
        row = r;
    }
    public void setCol(int c){
        col = c;
    }

    //Compare this tile to another tile and compare if they are connected
    public boolean isConnected(PipeTile A){

        PipeTile B = this;

        //check to make sure they are adjacent
        //by confirming they are on the same row
        //and that they are only 1 column away from each other
        if(A.getRow() == B.getRow()){

            //given they are adjacent and one the same row
            //compare if A.isRight = true && B.isLeft

            //this tells me that A Tile is to the left of B
            if(A.getCol()+1 ==B.getCol() && A.isLeft()&&B.isRight()){
                return true; // A is to the left of B and is on the same row
            }
            //This tells me that B tile is to the left of A
            else if(A.getCol()-1==B.getCol() && A.isRight()&&B.isLeft()){
                return true;
            }

        }
        //check to make sure they are adjacent
        //by confirming they are on the same column
        //and that they are only 1 row away from each other
        else if(A.getCol()==B.getCol()){

            //given they are adjacent and one the same row
            //compare if A.isRight = true && B.isLeft

            //this tells me that A Tile is below B
            if(A.getRow()+1 ==B.getRow() && A.isUp()&&B.isDown()){
                return true; // A is below B and is on the same column
            }
            //This tells me that B tile is below A
            else if(A.getRow()-1==B.getCol() && A.isDown()&&B.isUp()){
                return true; // B is below A and is on the same column
            }




        }

        return false;
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public Color getTileBack() {
        return TileBack;
    }

    public void setTileBack(Color tileBack) {
        TileBack = tileBack;
    }
    
    public Color getEmptyPipe(){
        return emptyPipe;
    }

    public void setEmptyPipe(Color c){
        emptyPipe = c;
    }

    public Color getFullPipe(){
        return fullPipe;
    }
    public Color setFullPipe(Color c){
       return fullPipe=c;
    }

    public boolean isRight() {
        return isRight;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public boolean isDown() {
        return isDown;
    }

    public boolean isUp() {
        return isUp;
    }

    public int getPipeSize() {
        return pipeSize;
    }

    public void setPipeSize(int pipeSize) {
        this.pipeSize = pipeSize;
    }
}

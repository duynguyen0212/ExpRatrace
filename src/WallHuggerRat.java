
/**   This rat hates going left so instead of doing things randomly,
 *    it always goes right relative to its orientation.
 *    The orientation can be represented by an int variable with 4 values,
 *    for example: (0, 1, 2, 3) for (Up, Down, Left, Right) facing.
 *
 */

public class WallHuggerRat implements Animal {
    // constructors
    public WallHuggerRat() {
    }
    int        startCol   = 0;
    int        startRow   = 0;
    int        currentCol = 0;
    int        currentRow = 0;
    String     name       = "Peter";
    int        numMoves   = 0;
    // orientation
    String[] direction = {"N", "E", "S", "W"};
    int directionIndex = 0;
    String forward = direction[directionIndex]; // represent current direction
    // returns current row animal is in
    public int getRow(){ return currentRow; }

    // returns current column animal is in
    public int getColumn() { return currentCol; }

    // returns one letter to represent animal
    public char getLetter() { return name.charAt(0); }

    // returns animal's name
    public String getName(){ return name; }

    // returns # moves animal has made in maze so far
    public int getNumMoves() { return numMoves;  }

    // returns column where animal started in maze
    public int getStartColumn(){  return startCol;  }

    // returns row where animal started in maze
    public int getStartRow(){ return startRow; }

    // asks animal to make a move in this maze. This is called by the Maze
    public void move(Maze maz){
        boolean noMoveFound = true;
        if( !maz.ratHasEscaped())
        {

            while (noMoveFound) {
                if(lookRight(maz)) // facing forward initially, move right
                {   turnRight();
                    moveForward();
                    noMoveFound = false;
                }
                else if(lookForward(maz)) // if mouse can't move right, move forward
                {
                    moveForward();
                    noMoveFound = false;
                }
                else if(lookLeft(maz)) // if mouse can't move forward, move left
                {   turnLeft();
                    moveForward();
                    noMoveFound = false;
                }

                // none of above, mouse turn around/ move down
                else {
                    turnRight();
                    turnRight();
                    moveForward();
                    noMoveFound = false;
                }
            }
            numMoves++;
        }
    }
    private void turnRight()
    {
        directionIndex = (directionIndex + 1) % 4;
        forward = direction[directionIndex];
    }

    private void turnLeft()
    {
        directionIndex = (directionIndex + 3) % 4;
        forward = direction[directionIndex];
    }
    public void moveForward(){

        if (forward.equals("N"))
            currentRow--;
        else if (forward.equals("E"))
            currentCol++;
        else if (forward.equals("S"))
            currentRow++;
        else // forward equals "W"
            currentCol--;
    }

    public boolean lookRight(Maze maz){
        if ( forward.equals("N") )
            return maz.canMove(currentRow, currentCol +1);
        else if ( forward.equals("E") )
            return maz.canMove(currentRow +1, currentCol);
        else if ( forward.equals("S") )
            return maz.canMove(currentRow, currentCol -1);
        else // forward equals "W"
            return maz.canMove(currentRow -1, currentCol);
    }
    public boolean lookForward(Maze maz){
        if ( forward.equals("N") )
            return maz.canMove(currentRow -1, currentCol );
        else if ( forward.equals("E") )
            return maz.canMove(currentRow, currentCol +1);
        else if ( forward.equals("S") )
            return maz.canMove(currentRow +1, currentCol);
        else // forward equals "W"
            return maz.canMove(currentRow, currentCol -1);
    }
    public boolean lookLeft(Maze maz){
        if ( forward.equals("N") )
            return maz.canMove(currentRow, currentCol -1);
        else if ( forward.equals("E") )
            return maz.canMove(currentRow -1, currentCol);
        else if ( forward.equals("S") )
            return maz.canMove(currentRow, currentCol +1);
        else // forward equals "W"
            return maz.canMove(currentRow +1, currentCol);
    }

    // moves animal back to starting row/column, wipes # moves to 0
    public void reset() {
        currentRow = startRow;
        currentCol = startCol;
        numMoves = 0;
    }
    // sets column where animal started in maze
    public void setStartColumn(int col) { startCol = col; }

    // sets row where animal started in maze
    public void setStartRow(int row){ startRow = row; }
}

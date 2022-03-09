/**
 * This rat is has a small amount of memory that allows it to remember where it has been.
 */

import java.util.Random;

public class RandomRat implements Animal {

    static Random rnd = new Random();

    // constructors
    public RandomRat() {
    }

    int startCol = 0;
    int startRow = 0;
    int currentCol = 0;
    int currentRow = 0;
    String name = "Jerry";
    int numMoves = 0;

    // orientation
    String[] direction = {"N", "E", "S", "W"};
    int directionIndex = 0;
    String forward = direction[directionIndex]; // represent current direction

    // returns current row animal is in
    public int getRow() {
        return currentRow;
    }

    // returns current column animal is in
    public int getColumn() {
        return currentCol;
    }

    // returns one letter to represent animal
    public char getLetter() {
        return name.charAt(0);
    }

    // returns animal's name
    public String getName() {
        return name;
    }

    // returns # moves animal has made in maze so far
    public int getNumMoves() {
        return numMoves;
    }

    // returns column where animal started in maze
    public int getStartColumn() {
        return startCol;
    }

    // returns row where animal started in maze
    public int getStartRow() {
        return startRow;
    }

    // asks animal to make a move in this maze. This is called by the Maze
    public void move(Maze maz) {
        boolean noMoveFound = true;

        while (noMoveFound) {
            switch (rnd.nextInt(4)) {
                case 0:
                    if (lookRight(maz)) {
                        turnRight();

                        moveForward();
                        noMoveFound = false;
                    }
                    break;
                case 1:
                    if (lookForward(maz)) {

                        moveForward();
                        noMoveFound = false;
                    }
                    break;
                case 2:
                    if (lookLeft(maz)) {
                        turnLeft();

                        moveForward();
                        noMoveFound = false;
                    }
                    break;
                case 03:
                    if (!lookForward(maz) && !lookLeft(maz) && !lookRight(maz)) {
                        turnRight();
                        turnRight();
                        moveForward();
                        noMoveFound = false;
                    }
            }
        }
        numMoves++;
    }

    /**
     * This method is used to change the mouse direction
     * for example: mouse is facing north, turn right will be east
     * {"N", "E", "S", "W"}
     *   0    1    2    3
     * direction index of north is (0 + 1)%4 = 1 -> mouse now facing north
     */
    private void turnRight() {
        directionIndex = (directionIndex + 1) % 4;
        forward = direction[directionIndex];
    }

    /**
     * This method is also used to change mouse direction to the left
     * base on current direction
     */
    private void turnLeft() {
        directionIndex = (directionIndex + 3) % 4;
        forward = direction[directionIndex];
    }

    /**
     * This method will let the mouse know where to move based on which direction its facing
     */
    public void moveForward() {

        if (forward.equals("N")) {
            currentRow--;
        } else if (forward.equals("E")) {
            currentCol++;
        } else if (forward.equals("S")) {
            currentRow++;
        } else // forward equals "W"
        {
            currentCol--;
        }
    }

    /**
     * Check on the right (base on current direction)
     * if the mouse can move to that coordinate in the maze
     * the code will be similar for look forward and look left
     *
     * @param maz
     */
    public boolean lookRight(Maze maz) {
        if (forward.equals("N")) {
            return maz.canMove(currentRow, currentCol + 1);
        } else if (forward.equals("E")) {
            return maz.canMove(currentRow + 1, currentCol);
        } else if (forward.equals("S")) {
            return maz.canMove(currentRow, currentCol - 1);
        } else // forward equals "W"
        {
            return maz.canMove(currentRow - 1, currentCol);
        }
    }

    /**
     * Check ahead to see if the mouse can move up
     *
     * @param maz
     */
    public boolean lookForward(Maze maz) {
        if (forward.equals("N")) {
            return maz.canMove(currentRow - 1, currentCol);
        } else if (forward.equals("E")) {
            return maz.canMove(currentRow, currentCol + 1);
        } else if (forward.equals("S")) {
            return maz.canMove(currentRow + 1, currentCol);
        } else // forward equals "W"
        {
            return maz.canMove(currentRow, currentCol - 1);
        }
    }

    /**
     * Check on left to see if the mouse can move to the left
     *
     * @param maz
     */
    public boolean lookLeft(Maze maz) {
        if (forward.equals("N")) {
            return maz.canMove(currentRow, currentCol - 1);
        } else if (forward.equals("E")) {
            return maz.canMove(currentRow - 1, currentCol);
        } else if (forward.equals("S")) {
            return maz.canMove(currentRow, currentCol + 1);
        } else // forward equals "W"
        {
            return maz.canMove(currentRow + 1, currentCol);
        }
    }


    // moves animal back to starting row/column, wipes # moves to 0
    public void reset() {
        currentRow = startRow;
        currentCol = startCol;
        numMoves = 0;
    }

    // sets column where animal started in maze
    public void setStartColumn(int col) {
        startCol = col;
    }

    // sets row where animal started in maze
    public void setStartRow(int row) {
        startRow = row;
    }
}
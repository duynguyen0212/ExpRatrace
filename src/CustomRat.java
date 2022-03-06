
/**
 * This rat is has a small amount of memory that allows it to remember where it has been.
 * It’s recommended that this memory be implemented as a single variable
 * as you only need to remember the previous position.
 */

import java.util.*;

public class CustomRat implements Animal {

    static Random rnd = new Random();

    // constructors
    public CustomRat() {

    }

    int startCol = 0;
    int startRow = 0;
    int currentCol = 0;
    int currentRow = 0;
    String name = "Terry";
    int numMoves = 0;
    public String solution;
    String[] direction = {"L", "R", "U", "D"};
    String put;
    String add = "";
    public Queue<String> nums = new LinkedList<>();



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
        if(nums.isEmpty()){
            nums.add("");
        }
        boolean noMoveFound = true;

        if (!findEnd(maz, add)) {
            add = nums.remove();
            for (String j : direction) {
                put = add + j;
                if (valid(maz, put)) {
                    nums.add(put);
                }
            }
        }
        else
        {
            if(!maz.ratHasEscaped()){
                while(noMoveFound)
                {
                    char c = solution.charAt(numMoves);
                    if (c == 'L') {
                        currentCol--;
                        noMoveFound = false;

                    } else if (c == 'R') {
                        currentCol++;
                        noMoveFound = false;

                    } else if (c == 'U') {
                        currentRow--;
                        noMoveFound = false;

                    } else if (c == 'D') {
                        currentRow++;
                        noMoveFound = false;

                    }
                    numMoves++;
                }
            }
        }

    }

    public boolean valid(Maze maz, String moves) {
        int i = currentCol;
        int j = currentRow;
        for (int k = 0; k < moves.length(); k++) {
            char c = moves.charAt(k);
            if (c == 'L') {
                i -= 1;
            } else if (c == 'R') {
                i += 1;
            } else if (c == 'U') {
                j -= 1;
            } else if (c == 'D') {
                j += 1;
            }
            if (!maz.contains(j, i)) {
                return false;
            } else if (!maz.canMove(j, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean findEnd(Maze maz, String moves) {
        int i = currentCol;
        int j = currentRow;

        for (int k = 0; k < moves.length(); k++) {
            char c = moves.charAt(k);
            if (c == 'L') {
                i -= 1;
            } else if (c == 'R') {
                i += 1;
            } else if (c == 'U') {
                j -= 1;
            } else if (c == 'D') {
                j += 1;
            }
        }
        if (maz.getSquare(j, i) == Maze.FINISH) {
            solution = moves;
            return true;
        }
        return false;
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
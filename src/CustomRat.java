/**
 * This rat is will use Breath First Search method
 * Solution will contain a string which will lead to the exit
 * BFS will take around 1 minute 10 seconds (with current maze size) to find the shortest path
 */

import java.util.*;

public class CustomRat implements Animal {

    // constructors
    // this constructor will give current position of the mouse
    public CustomRat(int x, int y) {
        this.currentCol = x;
        this.currentRow = y;
    }

    public CustomRat() {

    }

    int startCol = 0;
    int startRow = 0;
    int currentCol = 0;
    int currentRow = 0;
    String name = "Terry";
    int numMoves = 0;

    public String solution;
    public boolean solutionFound = false;
    String[] direction = {"L", "R", "U", "D"};
    String nextMove;
    String coordinate = "";
    public Queue<String> nums = new LinkedList<>();
    public ArrayList<CustomRat> visited = new ArrayList<>();

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

    /**
     * Breath first search method
     * add will initially blank but will receive a direction from the Queue
     * nextMove = coor + (a direction) and put will be checked if the direction is can move or not
     * for example: in maze 1, put = UU will return true
     * all the possible path will be added to Queue 'nums'
     *
     * @param maz
     */
    public void BFS(Maze maz) {
        if (!findEnd(maz, coordinate)) {
            coordinate = nums.remove();
            for (String j : direction) {
                nextMove = coordinate + j;
                if (valid(maz, nextMove)) {
                    nums.add(nextMove);
                }
            }
        }
    }

    /**
     * check if the mouse can move in each direction "left, right, up, down"
     *
     * @param maz
     * @param moves
     * @return valid
     */
    public boolean valid(Maze maz, String moves) {

        int i = currentCol;
        int j = currentRow;
        CustomRat currentPos = new CustomRat(i, j);
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
        CustomRat check = new CustomRat(i, j);
        // this if statement check if the following moves are in the maze, not a wall and not visited
        if (!maz.contains(j, i)) {
            return false;
        } else if (!maz.canMove(j, i)) {
            return false;
        } else if (isVisited(check)) {
            return false;
        } else {
            visited.add(check);
            return true;
        }
    }

    /**
     * Check if given location is already visited or not
     *
     * @param location
     * @return flag
     */
    public boolean isVisited(CustomRat location) {
        boolean flag = false;
        for (int i = 0; i < visited.size(); i++) {
            if (visited.get(i).currentRow == location.currentRow && visited.get(i).currentCol == location.currentCol) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * check if the current index match with FINISH 'F' in the maze
     * if current index is match -> set solutionFound to true and return solution string
     *
     * @param maz
     * @param moves
     * @return findEnd
     */
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
            solutionFound = true;
            return true;
        }
        return false;
    }

    // asks animal to make a move in this maze. This is called by the Maze
    public void move(Maze maz) {
        boolean noMoveFound = true;

        // Add an empty string to nums so method findEnd can be executed
        if (nums.isEmpty()) {
            nums.add("");
        }

        // Breath first search is called when solution is not found
        if (!solutionFound) {
            BFS(maz);
        } else {

            if (!maz.ratHasEscaped()) {
                while (noMoveFound) {
                    char c = solution.charAt(numMoves);
                    // Mouse will:
                    // turn left if character is 'L'
                    // turn right if character is 'R', etc.
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
package abalone;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


import abalone.gameEnum.Layout;
import abalone.gameEnum.Direction;
import abalone.gameEnum.MarbleType;
import abalone.gameEnum.TURN;

/**
 * 
 * @author Kimdoy
 *
 *         Class Board.
 * 
 *         This class will be the foundation of the game Abalone. The game is
 *         arranged similar to Chinese checkers but without the starting thus
 *         resulting in a hexagon with 5 units on each side. To account for the
 *         diagonal layout of the game, the board is made up of 2D array of
 *         cells with some of the cells being redundant...
 * 
 *         --------------------- //The '-' represents redundant cells
 *         ------O-O-O-O-O------ //the 'O' represents active cells
 *         -----O-O-O-O-O-O----- ----O-O-O-O-O-O-O---- ---O-O-O-O-O-O-O-O---
 *         --O-O-O-O-O-O-O-O-O-- ---O-O-O-O-O-O-O-O--- ----O-O-O-O-O-O-O----
 *         -----O-O-O-O-O-O----- ------O-O-O-O-O------ ---------------------
 * 
 *         Due to this nature, many for loops had to be created to set up the
 *         board.
 */
public class Board {

    public static final int NUMBER_OF_ROWS = 11;
    public static final int NUMBER_OF_COLUMNS = 21;
    public static TURN PLAYER_TURN = TURN.PLAYER1;
    public static int layout_number = 0;

    private ArrayList<Marble> marbles;
    private HashMap<Direction, Point> moveSet;
    private Cell[][] cell;
    private Point firstCoordinate;
    private Point secondCoordinate;
    private Stack<Marble> stackOfMarbles;

    private int num_move_player1 = 0;
    private int num_move_player2 = 0;

    
    
    // Constructor for class Board.
    public Board() {
        cell = new Cell[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
        marbles = new ArrayList<Marble>();
        moveSet = new HashMap<Direction, Point>();
        firstCoordinate = new Point();
        secondCoordinate = new Point();
        init();
    }

    // Initializes the board.
    public void init() {
        setBoard();
        
        if(layout_number == 0)
            standardLayout();
        else if(layout_number ==1)
            belgiumDaisyLayout();
        else if(layout_number == 2)
            germanDaisyLayout();
        
        initializeMoveSets();
    }

    /**
     * Returns the Cell at the specified index. Does not check whether if the Cell
     * is null.
     * 
     * @param x
     *            as an int representing rows
     * @param y
     *            as an int representing columns
     * @return Cell
     */
    public Cell getCellAt(int x, int y) {
        return cell[x][y];
    }

    /**
     * ArrayList that stores Marbles. This List should only contain The marbles that
     * are in que to be moved.
     * 
     * @return ArrayList of Marble
     */
    public ArrayList<Marble> getMarbles() {
        return marbles;
    }

    /**
     * Adds the Marble in the parameter, will also set the color of the marble to be
     * darker to specify that the marble is currently highlighted.
     * 
     * @param marble
     */
    public void addMarbles(Marble marble) {
        marble.setDarkColor();
        marbles.add(marble);
    }

    /**
     * Clears the ArrayList of marbles.
     */
    public void clearMarbles() {
        marbles.clear();
    }

    public Stack<Marble> getMarbleStack() {
        return stackOfMarbles;
    }

    public void addMarbleToStack(Marble marble) {
        stackOfMarbles.push(marble);
    }

    /**
     * HashMap of Direction and Point. The enum Direction has 6 possibilities which
     * represent the possible moves in the game Abalone. The Point contains and x
     * and y which represents the coordinate change dependant on the Direction.
     * 
     * @return HashMaps<Direction, Point>
     */
    public HashMap<Direction, Point> getMoveSets() {
        return moveSet;
    }

    /**
     * Initializes the HashMap accordingly.
     */
    public void initializeMoveSets() {
        moveSet.put(Direction.LEFT, new Point(0, -2));
        moveSet.put(Direction.RIGHT, new Point(0, 2));
        moveSet.put(Direction.TOP_LEFT, new Point(-1, -1));
        moveSet.put(Direction.TOP_RIGHT, new Point(-1, 1));
        moveSet.put(Direction.BOT_LEFT, new Point(1, -1));
        moveSet.put(Direction.BOT_RIGHT, new Point(1, 1));
    }

    /**
     * firstCoordinate will always contain the position of the first marble that is
     * added to the ArrayList marbles.
     * 
     * @return Point
     */
    public Point getFirstCoordinates() {
        return firstCoordinate;
    }

    /**
     * This method is only to be called when adding the FIRST element to the
     * ArrayList marbles. It will store the coordinates of the cell of the specific
     * marble.
     * 
     * @param x
     *            as an int for rows
     * @param y
     *            as an int for columns
     */
    public void setFirstCoordinates(int x, int y) {
        firstCoordinate = new Point(x, y);
    }

    /**
     * secondCoordinate will always contain the position of the Cell that a Marble
     * is trying to move to.
     * 
     * @return Point
     */
    public Point getSecondCoordinates() {
        return secondCoordinate;
    }

    /**
     * This method is only to be called when MOVING the marbles.
     * 
     * @param x
     * @param y
     */
    public void setSecondCoordinates(int x, int y) {
        secondCoordinate = new Point(x, y);
    }

    /**
     * setBoard will only instantiate specific Cells. These Cells are chosen by how
     * the board is layed out. Look at the class comment to see how the cells are
     * laid out.
     */
    public void setBoard() {

        int counter = 2;

        for (int i = 5; i < NUMBER_OF_ROWS - 1; i++) {
            for (int j = counter; j < 24 - i; j++) {
                if (legalSpace(i, j)) {
                    cell[i][j] = new Cell(i, j, this);
                }
            }
            counter++;
        }

        counter = 6;
        for (int i = 1; i < 5; i++) {
            for (int j = counter; j < 14 + i; j++) {
                if (legalSpace(i, j)) {
                    cell[i][j] = new Cell(i, j, this);
                }
            }
            counter--;
        }
    }
    
    public void standardLayout() {
        placeRedMarbles();
        placeBlueMarbles();
    }

    public void belgiumDaisyLayout() {
        placeBlueMarblesDaisy(Layout.BELGIUM_DAISY);
        placeRedMarblesDaisy(Layout.BELGIUM_DAISY);
    }
    
    public void germanDaisyLayout() {
        placeBlueMarblesDaisy(Layout.GERMAN_DAISY);
        placeRedMarblesDaisy(Layout.GERMAN_DAISY);
    }

    public void setMarbles(ArrayList<Cell> cells, MarbleType color) {
        if (color.equals(MarbleType.BLACK)) {
            for (Cell cell : cells) {
                cell.setMarble(new Marble(this, cell, MarbleType.BLACK));
            }
        } else if (color.equals(MarbleType.WHITE)) {
            for (Cell cell : cells) {
                cell.setMarble(new Marble(this, cell, MarbleType.WHITE));
            }
        }
    }

    /**
     * Places the Red Marbles onto the board.
     */
    public void placeRedMarbles() {
        ArrayList<Cell> space = new ArrayList<Cell>();
        for (int i = 6; i < 15; i++) {
            if (legalSpace(1, i))
                space.add(cell[1][i]); //
        }
        for (int i = 5; i < 16; i++) {
            if (legalSpace(2, i))
                space.add(cell[2][i]); //
        }
        for (int i = 8; i < 13; i++) {
            if (legalSpace(3, i))
                space.add(cell[3][i]); //
        }
        setMarbles(space, MarbleType.WHITE);
    }

    /**
     * Places the Blue Marbles onto the board.
     */
    public void placeBlueMarbles() {
        ArrayList<Cell> space = new ArrayList<Cell>();
        for (int i = 8; i < 13; i++) {
            if (legalSpace(7, i))
                space.add(cell[7][i]); //
        }
        for (int i = 5; i < 16; i++) {
            if (legalSpace(8, i))
                space.add(cell[8][i]); //
        }
        for (int i = 6; i < 15; i++) {
            if (legalSpace(9, i))
                space.add(cell[9][i]); //
        }
        setMarbles(space, MarbleType.BLACK);
    }

    public void placeBlueMarblesDaisy(Layout layout) {
        ArrayList<Cell> cells = new ArrayList<Cell>();
        Cell firstCell = null;
        Cell secondCell = null;
        if (layout == Layout.BELGIUM_DAISY) {
            firstCell = cell[2][7];
            secondCell = cell[8][13];
        } else if(layout == Layout.GERMAN_DAISY) {
            firstCell = cell[3][6];
            secondCell = cell[7][14];
        }

        cells.addAll(getNeighbouringCells(firstCell));
        cells.addAll(getNeighbouringCells(secondCell));
        cells.add(firstCell);
        cells.add(secondCell);

        setMarbles(cells, MarbleType.BLACK);
    }

    public void placeRedMarblesDaisy(Layout layout) {
        ArrayList<Cell> cells = new ArrayList<Cell>();
        Cell firstCell = null;
        Cell secondCell = null;
        if(layout == Layout.BELGIUM_DAISY) {
            firstCell = cell[2][13];
            secondCell = cell[8][7];
        } else if(layout == Layout.GERMAN_DAISY) {
            firstCell = cell[3][14];
            secondCell = cell[7][6];
        }
        cells.addAll(getNeighbouringCells(firstCell));
        cells.addAll(getNeighbouringCells(secondCell));
        cells.add(firstCell);
        cells.add(secondCell);

        setMarbles(cells, MarbleType.WHITE);
    }

    /**
     * Iterates through the entire 2D Array of Cells and will return an ArrayList of
     * all NON Cells.
     * 
     * @return ArrayList<Cell>
     */
    public ArrayList<Cell> getCells() {
        ArrayList<Cell> cells = new ArrayList<Cell>();

        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                if (legalSpace(i, j) && cell[i][j] != null) {
                    cells.add(cell[i][j]);
                }
            }
        }
        return cells;
    }

    /**
     * Looks around the Cell specified in the parameter and checks to see if it has
     * neighbors. A cell is considered a neighbour if and only if the Cell is beside
     * the current scoped Cell. It will add all Cell neighbours into an ArrayList
     * and return that list.
     * 
     * Also makes sure to not add any null cells.
     * 
     * @param cell
     * @return ArrayList<Cell>
     */
    public ArrayList<Cell> getNeighbouringCells(Cell cell) {

        ArrayList<Cell> neighbouringCells = new ArrayList<Cell>();
        int x = cell.getX();
        int y = cell.getY();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 || j == 0)
                    continue;
                if (this.cell[x + i][y + j] != null) {
                    neighbouringCells.add(this.cell[x + i][y + j]);
                }
            }
        }

        if (this.cell[x][y + 2] != null)
            neighbouringCells.add(this.cell[x][y + 2]);
        if (this.cell[x][y - 2] != null)
            neighbouringCells.add(this.cell[x][y - 2]);

        return neighbouringCells;
    }

    /**
     * This method will iterate through the getNeighbouringCells method and only add
     * the ones that does NOT contain a marble.
     * 
     * @param cell
     * @return ArrayList<Cell>
     */
    public ArrayList<Cell> getNeighbouringEmptyCells(Cell cell) {

        ArrayList<Cell> emptyCells = new ArrayList<Cell>();
        ArrayList<Cell> neighbouringCells = getNeighbouringCells(cell);

        for (Cell emptyCell : neighbouringCells) {
            if (emptyCell.getMarble() == null) {
                emptyCells.add(emptyCell);
            }
        }

        return emptyCells;
    }

    /**
     * Opposite of the method above. Iterates through the getNeighbouringCells
     * method and only adds the Cells that DO contain a marble.
     * 
     * @param cell
     * @return ArrayList<Marble>
     */
    public ArrayList<Marble> getNeighbouringMarbles(Cell cell) {

        ArrayList<Marble> marbleCells = new ArrayList<Marble>();
        ArrayList<Cell> neighbouringCells = getNeighbouringCells(cell);

        for (Cell marbleCell : neighbouringCells) {
            if (marbleCell.getMarble() != null) {
                marbleCells.add(marbleCell.getMarble());
            }
        }

        return marbleCells;
    }

    /**
     * NOTE: MAKE SURE THE CELL YOU USE FOR THIS METHOD CONTAINS A MARBLE.
     * 
     * Iterates through the method getNeighbouringMarbles and adds all marbles that
     * are the same color as the marble in the Cell.
     * 
     * @param cell
     * @return ArrayList<Marble>
     */
    public ArrayList<Marble> getNeighbouringFriends(Cell cell) {

        ArrayList<Marble> friends = new ArrayList<Marble>();
        ArrayList<Marble> neighbouringMarbles = getNeighbouringMarbles(cell);

        for (Marble friend : neighbouringMarbles) {
            if (friend.getType() == cell.getMarble().getType()) {
                friends.add(friend);
            }
        }

        return friends;
    }

    /**
     * NOTE: MAKE SURE THE CELL YOU USE FOR THIS METHOD CONTAINS A MARBLE.
     * 
     * Iterates through the method getNeighbouringMarbles and adds all marbles that
     * are NOT the same color as the marble in the Cell.
     * 
     * @param cell
     * @return ArrayList<Marble>
     */
    public ArrayList<Marble> getNeighbouringEnemies(Cell cell) {

        ArrayList<Marble> enemies = new ArrayList<Marble>();
        ArrayList<Marble> neighbouringMarbles = getNeighbouringMarbles(cell);

        for (Marble enemy : neighbouringMarbles) {
            if (enemy.getType() != cell.getMarble().getType()) {
                enemies.add(enemy);
            }
        }

        return enemies;
    }

    /**
     * return the next cell at certain direction
     * 
     * @param cell
     * @param direction
     * @return
     */
    public Cell getDestinationCell(Cell cell, Direction direction) {
        int xDirection = getMoveSets().get(direction).x;
        int yDirection = getMoveSets().get(direction).y;
        int xDestination = cell.getX() + xDirection;
        int yDestination = cell.getY() + yDirection;
        return getCellAt(xDestination, yDestination);
    }

    public ArrayList<Marble> findInlineEnemies(Marble m, Direction direction) {
        ArrayList<Marble> enemies = new ArrayList<Marble>();
        int xDirection = getMoveSets().get(direction).x;
        int yDirection = getMoveSets().get(direction).y;
        int x = m.getCell().getX() + xDirection;
        int y = m.getCell().getY() + yDirection;
        Cell cell = getCellAt(x, y);

        while (cell != null && cell.getMarble() != null) {

            if (!(cell.getMarble().getType().equals(m.getType()))) {
                enemies.add(cell.getMarble());
            } else {
                break;
            }
            x += xDirection;
            y += yDirection;
            cell = getCellAt(x, y);
        }
        return enemies;
    }

    /**
     * Will only return true if the given coordinate contains an ODD number AND an
     * EVEN number ei (1, 2), (3,6) etc.
     * 
     * This algorithm works because of how our 2D array is laid out.
     * 
     * @param x
     * @param y
     * @return
     */
    public boolean legalSpace(int x, int y) {
        if ((x % 2 == 0 && y % 2 == 1) || (x % 2 == 1 && y % 2 == 0)) {
            return true;
        }
        return false;
    }

    /**
     * Increment of number of movement for each players
     */
    public void setNumOfMove() {
        if (Board.PLAYER_TURN == TURN.PLAYER1)
            ++num_move_player1;
        else
            ++num_move_player2;
    }

    /**
     * Return number of movement for each players turn
     * 
     * @param turn
     * @return
     */
    public int getNumOfMove(TURN turn) {
        if (turn == TURN.PLAYER1)
            return num_move_player1;
        else
            return num_move_player2;
    }

}

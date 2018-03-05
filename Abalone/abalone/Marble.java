package abalone;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import abalone.gameEnum.Direction;
import abalone.gameEnum.MarbleType;

@SuppressWarnings("serial")
public class Marble extends JPanel {
    
    public static final Color RED       = new Color(225, 0, 0);
    public static final Color DARK_RED  = new Color(153, 0, 0);
    public static final Color BLUE      = new Color(0, 0, 225);
    public static final Color DARK_BLUE = new Color(0, 0, 153);

    private Board board;
    private Cell cell;
    private MarbleType type;
    private Color color;

    public Marble() {
    }

    public Marble(Board board, Cell cell, MarbleType type) {
        this.board = board;
        this.cell = cell;
        this.type = type;
        init();
    }

    public void init() {
        if (type == MarbleType.RED) {
            color = RED;
        } else if (type == MarbleType.BLUE) {
            color = BLUE;
        }
    }
    
    public Board getBoard() {
        return board;
    }

    public MarbleType getType() {
        return type;
    }

    public Cell getCell() {
        return cell;
    }
    
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Color getColor() {
        return color;
    }
    
    public void setNormalColor() {
        if(getType() == MarbleType.RED) color = RED;
        if(getType() == MarbleType.BLUE) color = BLUE;
    }
    
    public void setDarkColor() {
        if(getType() == MarbleType.RED) color = DARK_RED;
        if(getType() == MarbleType.BLUE) color = DARK_BLUE;
    }

    public static void clearCells(ArrayList<Marble> marbles) {
        for(Marble marble : marbles) {
            marble.setCell(null);
        }
    }
    

    // public void move1(ArrayList<Marble> marbles, Direction direction) {
    // Marble m1 = marbles.get(0);
    // int x = cell.getX() + board.getMoveSets().get(direction).x;
    // int y = cell.getY() + board.getMoveSets().get(direction).y;
    // board.getCellAt(x, y).setMarble(m1);
    // }
    //
    // public void move2(ArrayList<Marble> marbles, Direction direction) {
    // int xDirection = board.getMoveSets().get(direction).x;
    // int yDirection = board.getMoveSets().get(direction).y;
    // for(Marble marble : marbles) {
    // int x = marble.getCell().getX() + xDirection;
    // int y = marble.getCell().getY() + yDirection;
    // board.getCellAt(x, y).setMarble(marble);
    // }
    // }
    //
    // public void move3(ArrayList<Marble> marbles, Direction direction) {
    // int xDirection = board.getMoveSets().get(direction).x;
    // int yDirection = board.getMoveSets().get(direction).y;
    // for(Marble marble : marbles) {
    // int x = marble.getCell().getX() + xDirection;
    // int y = marble.getCell().getY() + yDirection;
    // board.getCellAt(x, y).setMarble(marble);
    // }
    // }

}

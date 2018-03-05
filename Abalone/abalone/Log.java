package abalone;

import java.awt.Point;
import java.util.ArrayList;

import abalone.gameEnum.Direction;
import abalone.gameEnum.MarbleType;
import boardFrame.GameFrame;

public class Log {
    public static ArrayList<String> blackHistory = new ArrayList<String>();
    public static ArrayList<String> whiteHistory = new ArrayList<String>();
    private String direction;
    private Marble marble;
    private Point point;
    private Board board;
    private String type;
    private String text;
    private String player;
    private int turn;

    public Log(Board board) {
        setBoard(board);
        setPlayer(board.getTurn().toString());
        setTurn(board.getNumOfMove(board.getTurn()) + 1);
        text = "Player = " + player + "\nTurn = " + turn + "\n";
    }

    public void setTurn(int turn) {
        if (turn >= 0) {
            this.turn = turn;
        }
    }

    public void setPlayer(String player) {
        if (player != null) {
            this.player = player;
        }
    }

    public void setMarbleType(MarbleType type) {
        if (type != null) {
            this.type = type.toString();
        }
    }

    public void setBoard(Board board) {
        if (board != null) {
            this.board = board;
        }
    }

    public void setDirection(String direction) {
        if (direction != null) {
            this.direction = direction;
        }
    }

    public void setMarble(Marble marble) {
        if (marble != null) {
            this.marble = marble;
        }
    }

    public void setPoint(Point point) {
        if (point != null) {
            this.point = point;
        }
    }

    public void addMove(Marble marble, Direction direction) {
        setMarble(marble);
        setMarbleType(marble.getType());
        setDirection(direction.toString());
        setPoint(board.getMoveSets().get(direction));
        
        int oldX = marble.getCell().getX();
        int oldY = marble.getCell().getY();
        int newX = oldX + point.x;
        int newY = oldY + point.y;
        
        text += type + " marble at [" + oldX + "][" + oldY
                + "] moved to " + direction + " at [" + newX + "][" + newY
                + "].\n";

    }

    public String getText() {
        return text;
    }

    public void addToLog() {
        if (type.equals("WHITE")) {
            whiteHistory.add(text);
        } else {
            blackHistory.add(text);
        }
    }
}

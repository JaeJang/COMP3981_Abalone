package listener;
import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;

import java.util.ArrayList;

import abalone.Board;
import abalone.Cell;
import abalone.gameEnum.MarbleType;
import abalone.gameEnum.STATE;
import boardFrame.GameFrame;




public class KeyInput extends KeyAdapter{

    private GameFrame frame;

    Board board;

    public KeyInput(GameFrame frame, Board board) {

        this.frame = frame;

        this.board = board;

    }

    

    public void keyPressed(KeyEvent e) {

        

        //Clicked button

        int key = e.getKeyCode();

        

        if(GameFrame.state == STATE.GAME) {

            

            //When user presses ESC button, menu is appeared

            if(key == KeyEvent.VK_ESCAPE) {

                GameFrame.state = STATE.PASUSE;

                frame.init();

            }

            if(key == KeyEvent.VK_SPACE) {

                ArrayList<Cell> list = board.getCells();

                for(Cell cell : list) {

                    if(cell.getMarble() != null) {

                        

                        if(cell.getMarble().getType() == MarbleType.BLACK) {

                            cell.setMarble(null);

                            frame.rePaint();

                            break;

                        }

                    }

                }
 
            }

        }

    }

    

}
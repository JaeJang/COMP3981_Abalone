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
    private static String userInput = "";

    Board board;

    public KeyInput(GameFrame frame, Board board) {

        this.frame = frame;

        this.board = board;

    }

    

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (GameFrame.state == STATE.TIME_SETTING ||GameFrame.state == STATE.TURN_LIMIT_SETTING) {
            char temp = (char) key;
            
            //if the key is pressed from numpad, convert it to proper number
            if(KeyEvent.KEY_LOCATION_NUMPAD == e.getKeyLocation()) {
                temp = (char) (key - 48);
            }
            
            //if the key is digit, add it to userInput string and print it
            if(Character.isDigit(temp)) {
                userInput += temp;
                frame.menu.takeTimeOrTurnlimit(userInput, false);
                frame.repaint();
            }
            
            //when back space is pressed
            if (key == KeyEvent.VK_BACK_SPACE) {
                if(!userInput.equals("")) {
                    userInput = userInput.substring(0, userInput.length() - 1); 
                    frame.menu.takeTimeOrTurnlimit(userInput, true);
                    frame.repaint();
                    
                }
            }
            
            if(key == KeyEvent.VK_ENTER) {
                if(!userInput.equals("")) {
                    if(GameFrame.state == STATE.TIME_SETTING) {
                        MyTimer.time_limit = frame.menu.getUserTyped(); 
                        resetUserInput();
                        GameFrame.state = STATE.TURN_LIMIT_SETTING;
                        frame.init();
                    } else {
                        GameFrame.turnLimit = frame.menu.getUserTyped();
                        resetUserInput();
                        GameFrame.state = STATE.GAME;
                        frame.init();
                    }
                } else if(userInput.equals("0")) {
                    if(GameFrame.state == STATE.TIME_SETTING) {
                        MyTimer.time_limit = Integer.MAX_VALUE; 
                        resetUserInput();
                        GameFrame.state = STATE.TURN_LIMIT_SETTING;
                        frame.init();
                    } else {
                        GameFrame.turnLimit = Integer.MAX_VALUE;
                        System.out.println(GameFrame.turnLimit);
                        resetUserInput();
                        GameFrame.state = STATE.GAME;
                        frame.init();
                    }
                }
            }
        }
        
        /*if (GameFrame.state == STATE.TURN_LIMIT_SETTING) {
            char temp = (char) key;
            
            if(KeyEvent.KEY_LOCATION_NUMPAD == e.getKeyLocation()) {
                temp = (char) (key - 48);
            }
            
            if(Character.isDigit(temp)) {
                userInput += temp;
                frame.menu.takeTimeOrTurnlimit(userInput, false);
                frame.repaint();
            }
            
            if (key == KeyEvent.VK_BACK_SPACE) {
                if(!userInput.equals("")) {
                    userInput = userInput.substring(0, userInput.length() - 1); 
                    frame.menu.takeTimeOrTurnlimit(userInput, true);
                    frame.repaint();
                    
                }
            }
            
            
        }*/

        else if(GameFrame.state == STATE.GAME) {

            

            //When user presses ESC button, menu is appeared

            if(key == KeyEvent.VK_ESCAPE) {

                GameFrame.state = STATE.PASUSE;

                frame.init();

            }

        }

    }

    public static void resetUserInput() {
        userInput = "";
    }

}
package listener;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import abalone.Board;
import abalone.gameEnum.STATE;
import abalone.gameEnum.TURN;
import boardFrame.GameFrame;

public class MouseInput extends MouseAdapter {

    GameFrame frame;

    public MouseInput(GameFrame frame) {

        this.frame = frame;

    }

    public void mousePressed(MouseEvent e) {

        // Clicked point

        int mx = e.getX();

        int my = e.getY();

        // Game state will be changed from FIRST_SCREEN to GAME

        // init() method in GameFrame will be called

        // Eventually, game board will be printed
        
      //Mode selection page
        if(GameFrame.state == STATE.MODE_SETTING) {
            //player vs player mode selected
            if (mouseOver(mx, my, 0, 40, 1800,170)) {
                GameFrame.state = STATE.TEAM_SETTING_PVP;
                frame.init();
            }
            //player vs computer mode selected
            if (mouseOver(mx, my, 0, 355, 1800,170)) {
                GameFrame.state = STATE.TEAM_SETTING_PVC;
                frame.init();
            }
            //computer vs computer mode selected
            if (mouseOver(mx, my, 0, 670, 1800,170)) {
                GameFrame.state = STATE.TEAM_SETTING_CVC;
                frame.init();
            }
        }
      //Select a black  player
        else if(GameFrame.state == STATE.TEAM_SETTING_PVP) {
            if (mouseOver(mx, my, 0, 355, 1800,170)) {
                
                GameFrame.state = STATE.LAYOUT_SELECT;
                frame.init();
            }
            
            if (mouseOver(mx, my, 0, 670, 1800,170)) {
                GameFrame.state = STATE.LAYOUT_SELECT;
                frame.init();
            }
        }
        
        //Select a black player or computer
        else if(GameFrame.state == STATE.TEAM_SETTING_PVC) {
            if (mouseOver(mx, my, 0, 355, 1800,170)) {
                GameFrame.state = STATE.LAYOUT_SELECT;
                frame.init();
            }
            
            if (mouseOver(mx, my, 0, 670, 1800,170)) {
                GameFrame.state = STATE.LAYOUT_SELECT;
                frame.init();
            }
        }
        
        //Select a black computer player
        else if(GameFrame.state == STATE.TEAM_SETTING_CVC) {
            if (mouseOver(mx, my, 0, 355, 1800,170)) {
                GameFrame.state = STATE.LAYOUT_SELECT;
                frame.init();
            }
            
            if (mouseOver(mx, my, 0, 670, 1800,170)) {
                GameFrame.state = STATE.LAYOUT_SELECT;
                frame.init();
            }
        }
        
        else if (GameFrame.state == STATE.LAYOUT_SELECT) {

            if (mouseOver(mx, my, 0, 40, 1800,170)) {
                
                Board.layout_number = 0;
                
                frame.setBoardToNew();
                
                GameFrame.state = STATE.TIME_SETTING;

                frame.init();
                

            } else if(mouseOver(mx, my, 0, 355, 1800,170)) {
                
                Board.layout_number = 1;
                
                frame.setBoardToNew();
                
                GameFrame.state = STATE.TIME_SETTING;
                
                frame.init();
                
                
            } else if(mouseOver(mx, my, 0, 670, 1800,170)) {
                
                Board.layout_number = 2;
                
                frame.setBoardToNew();
                
                GameFrame.state = STATE.TIME_SETTING;
                
                frame.init();
                
            }

        }
        
      //Time limit selection page
        else if(GameFrame.state == STATE.TIME_SETTING) {
            if (mouseOver(mx, my, 0, 40, 1800,170)) {
                MyTimer.time_limit = 30;
                GameFrame.state = STATE.TURN_LIMIT_SETTING;
                frame.init();
            }
            
            if (mouseOver(mx, my, 0, 355, 1800,170)) {
                MyTimer.time_limit = 40;
                GameFrame.state = STATE.TURN_LIMIT_SETTING;
                frame.init();
            }
            
            if (mouseOver(mx, my, 0, 670, 1800,170)) {
                MyTimer.time_limit = 60;
                GameFrame.state = STATE.TURN_LIMIT_SETTING;
                frame.init();
            }
        }
        
        else if(GameFrame.state == STATE.TURN_LIMIT_SETTING) {
            if (mouseOver(mx, my, 0, 40, 1800,170)) {
                GameFrame.state = STATE.GAME;
                GameFrame.turnLimit = 40;
                frame.init();
            }
            
            if (mouseOver(mx, my, 0, 355, 1800,170)) {
                GameFrame.state = STATE.GAME;
                GameFrame.turnLimit = 60;
                frame.init();
            }
            
            if (mouseOver(mx, my, 0, 670, 1800,170)) {
                GameFrame.state = STATE.GAME;
                GameFrame.turnLimit = 60;
                frame.init();
            }
        }

        else if (GameFrame.state == STATE.PASUSE) {

            if (mouseOver(mx, my, 0, 670, 1800,170)) {

                // Go back to the game

                GameFrame.state = STATE.GAME;

                frame.init();

            }

            if (mouseOver(mx, my, 0, 40, 1800,170)) {

                // Reset the game

                GameFrame.state = STATE.GAME;

                frame.setBoardToNew();

                frame.init();

            }
            
            if( mouseOver(mx, my, 0, 355, 1800,170)) {
                GameFrame.state = STATE.MODE_SETTING;
                
                frame.init();
            }

        }
        
        
        
        //Mode selection page
        /*else if(GameFrame.state == STATE.MODE_SETTING) {
            //player vs player mode selected
            if (mouseOver(mx, my, 0, 40, 1800,170)) {
                GameFrame.state = STATE.TEAM_SETTING_PVP;
                frame.init();
            }
            //player vs computer mode selected
            if (mouseOver(mx, my, 0, 355, 1800,170)) {
                GameFrame.state = STATE.TEAM_SETTING_PVC;
                frame.init();
            }
            //computer vs computer mode selected
            if (mouseOver(mx, my, 0, 670, 1800,170)) {
                GameFrame.state = STATE.TEAM_SETTING_CVC;
                frame.init();
            }
        }*/
        
        
    }

    // If a point clicked is inside of certain area(rectangle)

    // return true

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {

        if (mx > x && mx < x + width) {

            if (my > y && my < y + height) {

                return true;

            } else

                return false;

        } else

            return false;

    }

}
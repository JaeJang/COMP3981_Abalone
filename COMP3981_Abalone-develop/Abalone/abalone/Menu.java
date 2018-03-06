package abalone;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import abalone.gameEnum.STATE;
import boardFrame.GameFrame;

public class Menu extends JPanel{
    
    public static boolean menuSelected = false;
    /**
     * Return JPanel which is appeared when the program starts
     * 
     * @return
     */
    public JPanel startPanel() {
        
        return new JPanel() {
            public void paintComponent(Graphics g) {
                g.setColor(Color.white);
                g.fillRect(0, 40/2, 1800/2,170/2);
                g.setColor(Color.black);
                g.fillRect(0, 355/2, 1800/2,170/2);
                g.setColor(Color.white);
                g.fillRect(0, 670/2, 1800/2,170/2);
                
                
                g.setFont(new Font("Impact", 2/2, 150/2));
                g.setColor(Color.black);
                g.drawString("Standard", 640/2, 185/2);
                g.setColor(Color.white);
                g.drawString("Belgium Daisy", 480/2, 500/2);
                g.setColor(Color.black);
                g.drawString("German Daisy", 500/2, 815/2);
                
                
            }
        };
    }
    
    //
    /**
     *When user presses ESC button
     *the game is paused and menu is appeared 
     * 
     * @return
     */
    public JPanel pausePanel() {
        return new JPanel() {
            public void paintComponent(Graphics g) {
                g.setColor(Color.white);
                g.fillRect(0, 40/2, 1800/2,170/2);
                g.setColor(Color.black);
                g.fillRect(0, 355/2, 1800/2,170/2);
                g.setColor(Color.white);
                g.fillRect(0, 670/2, 1800/2,170/2);

                g.setFont(new Font("Impact", 2/2, 150/2));
                g.setColor(Color.black);
                g.drawString("Reset", 750/2, 185/2);
                g.setColor(Color.white);
                g.drawString("Main Menu", 600/2, 500/2);
                g.setColor(Color.black);
                g.drawString("Resume",670/2, 815/2);
            }
        };
    }
    
    @SuppressWarnings("serial")
    public JPanel timeSettingPanel() {
        return new JPanel() {
            public void paintComponent(Graphics g) {
                
                g.setColor(Color.white);
                g.fillRect(0, 40/2, 1800/2,170/2);
                g.setColor(Color.black);
                g.fillRect(0, 355/2, 1800/2,170/2);
                g.setColor(Color.white);
                g.fillRect(0, 670/2, 1800/2,170/2);
                
                g.setColor(Color.black);
                g.setFont(new Font("Impact", 2/2, 150/2));
                g.drawString("30 seconds / turn", 350/2, 185/2);
                g.setColor(Color.white);
                g.drawString("40 seconds / turn", 350/2, 500/2);
                g.setColor(Color.black);
                g.drawString("60 seconds / turn", 350/2, 815/2);
            }
        };
    }
    
    public JPanel gameModeSetPanel() {
        return new JPanel() {
            public void paintComponent(Graphics g) {
                g.setColor(Color.white);
                g.fillRect(0, 40/2, 1800/2,170/2);
                g.setColor(Color.black);
                g.fillRect(0, 355/2, 1800/2,170/2);
                g.setColor(Color.white);
                g.fillRect(0, 670/2, 1800/2,170/2);
                
                g.setColor(Color.black);
                g.setFont(new Font("Impact", 2/2, 150/2));
                g.drawString("Player vs Player", 395/2, 185/2);
                g.setColor(Color.white);
                g.drawString("Player vs Computer", 300/2, 500/2);
                g.setColor(Color.black);
                g.drawString("Computer vs Computer", 200/2, 815/2);
            }
        };
    }
    
    public JPanel teamSettingPanel() {
        return new JPanel() {
            public void paintComponent(Graphics g) {
                g.setColor(Color.white);
                g.fillRect(0, 40/2, 1800/2,170/2);
                g.setColor(Color.black);
                g.fillRect(0, 355/2, 1800/2,170/2);
                g.setColor(Color.black);
                g.fillRect(0, 670/2, 1800/2,170/2);
                
                g.setColor(Color.black);
                g.setFont(new Font("Impact", 2/2, 150/2));
                
                if(GameFrame.state == STATE.TEAM_SETTING_PVP) {
                    g.drawString("SELECT BLACK PLAYER", 260/2, 185/2);
                    g.setColor(Color.white);
                    g.drawString("Player 1 ", 650/2, 500/2);
                    g.drawString("Player 2", 650/2, 815/2);
                    
                } else if(GameFrame.state == STATE.TEAM_SETTING_PVC) {
                    g.drawString("SELECT BLACK PLAYER", 260/2, 185/2);
                    g.setColor(Color.white);
                    g.drawString("Player", 660/2, 500/2);
                    g.drawString("Computer", 530/2, 815/2);
                    
                }else if(GameFrame.state == STATE.TEAM_SETTING_CVC) {
                    g.drawString("SELECT BLACK PLAYER", 260/2, 185/2);
                    g.setColor(Color.white);
                    g.drawString("Computer 1", 500/2, 500/2);
                    g.drawString("Computer 2", 500/2, 815/2);
                }
            }
        };
    }
    
    public JPanel turnLimitSetting() {
        return new JPanel() {
            public void paintComponent(Graphics g) {
                g.setColor(Color.white);
                g.fillRect(0, 40/2, 1800/2,170/2);
                g.setColor(Color.black);
                g.fillRect(0, 355/2, 1800/2,170/2);
                g.setColor(Color.white);
                g.fillRect(0, 670/2, 1800/2,170/2);
                
                g.setColor(Color.black);
                g.setFont(new Font("Impact", 2/2, 150/2));
                g.drawString("40 TURNS / GAME", 350/2, 185/2);
                g.setColor(Color.white);
                g.drawString("50 TURNS / GAME", 350/2, 500/2);
                g.setColor(Color.black);
                g.drawString("60 TURNS / GAME", 350/2, 815/2);
            }
        };
    }
    
    
}

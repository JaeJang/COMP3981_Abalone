package listener;

import java.awt.Graphics;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import abalone.Board;
import abalone.Marble;
import abalone.gameEnum.STATE;
import abalone.gameEnum.TURN;
import boardFrame.GameFrame;

public class MyTimer extends TimerTask {
    
    public static int time_limit = 0;
    
    /**
     * Maximum minutes.
     */
    private static final int MAX_MIN = 60;

    /**
     * Maximum seconds.
     */
    private static final double MAX_SEC = 60.0;

    /**
     * Increment of times.
     */
    private static final int INCRE = 1;

    /**
     * Delay of timer class.
     */
    private static final int DELAY = 100;
    
    /**
     * Timer object.
     */
    private Timer time;
    private GameFrame frame;
    private Board board;
    
    private int msec;
    private int sec;
    
    private int player1_msec = 0;
    private int player2_msec = 0;
    private int player1_sec = 0;
    private int player2_sec = 0;
    private int player1_min = 0;
    private int player2_min = 0;
    
    public MyTimer(GameFrame frame) {
        msec = 0;
        sec = 0;
        this.frame = frame;
        this.board = frame.getBoard();
    }
    
    @Override
    public void run() {
        if(GameFrame.state == STATE.GAME) {
            msec += INCRE;
            if(sec >= time_limit) {
                frame.updateTotalTime();
                if(Board.PLAYER_TURN == TURN.PLAYER1) {
                    Board.PLAYER_TURN = TURN.PLAYER2;
                } else if(Board.PLAYER_TURN == TURN.PLAYER2) {
                    Board.PLAYER_TURN = TURN.PLAYER1;
                }
                
                sec = 0;
                msec = 0;
                resetMarbles();
            }
            
            if(msec >= 10) {
                msec = 0;
                ++sec;
            }
            
            if(GameFrame.turnOver) {
                msec = 0;
                sec = 0;
                GameFrame.turnOver = false;
            }
            
        
        frame.updateTime(sec, msec);
        frame.rePaint();
        }
    }
    
    public void setTimer() {
        if(time == null) {
            time = new Timer();
            time.schedule(new MyTimer(frame), new Date(), DELAY);
        }
    }
    
    public void resetMarbles() {
        for (Marble marble : board.getMarbles()) {
            marble.setNormalColor();
        }
        board.clearMarbles();
        frame.rePaint();
    }
    
    public String getPlayer1ToTal() {
        return player1_min + ":" + player1_sec + ":" + player1_msec;
    }
    
    public String getPlayer2ToTal() {
        return player2_min + ":" + player2_sec + ":" + player2_msec;
    }

}

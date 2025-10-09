import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import lib.panel.*;

public class MatchPicture extends JPanel {
    int currentScore = 0;
    int rows = 4;
    int columns = 4;
    int cardwidth = 90;
    int cardHeight = 90;

    int boardWidth = columns * cardwidth;
    int boardHeight = rows * cardHeight;

    JButton restartButton = new JButton();
    JPanel restartGamePanel = new JPanel();

    private panelStats statsPanel;
    private panelBoard boardPanel;
    private panelMath panelMath2Math;

    //  callback เวลาเกมจบ
    private Runnable onGameOver;

    public void setOnGameOver(Runnable onGameOver) {
        this.onGameOver = onGameOver;
    }


    public MatchPicture(String gameType,User currentPlayer) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(boardWidth, boardHeight));

        statsPanel = new panelStats(boardWidth,currentPlayer.getUsername());
        this.add(statsPanel, BorderLayout.NORTH);

        if (gameType.equals("kuromi")) {

        boardPanel = new panelBoard(restartButton, statsPanel);
        this.add(boardPanel, BorderLayout.CENTER);

        statsPanel.setOnTimeUp(() -> {
            boardPanel.restartGame();
            if (onGameOver != null) {
                statsPanel.stopTimer();
                boardPanel.stopTimer();
                this.currentScore = statsPanel.getScoreCount();
                onGameOver.run(); //  ไปหน้าถัดไป
                
            }
        });


        this.add(restartGamePanel, BorderLayout.SOUTH);
        
        } else if (gameType.equals("math")) {
        panelMath2Math = new panelMath(restartButton, statsPanel);
        this.add(panelMath2Math, BorderLayout.CENTER);

        statsPanel.setOnTimeUp(() -> {
            panelMath2Math.restartGame();
            if (onGameOver != null) {
                statsPanel.stopTimer();
                boardPanel.stopTimer();
                this.currentScore = statsPanel.getScoreCount();
                onGameOver.run(); //  ไปหน้าถัดไป
                
            }
        });


 
        this.add(restartGamePanel, BorderLayout.SOUTH);
        }

    }

    //  สั่งให้เวลาเริ่มนับหลังเข้าหน้าเกม
    public void startGame() {
        statsPanel.startTimer();
        boardPanel.startHide();
    }

    public int getCurrentScore(){ return this.currentScore;}
}

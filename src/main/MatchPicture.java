package main;

import java.awt.*;
import javax.swing.*;
import lib.User_Section.User;
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

    private Runnable onGameOver;

    public void setOnGameOver(Runnable onGameOver) {
        this.onGameOver = onGameOver;
    }

    // ✅ รับ setName เพื่อส่งต่อให้ panelBoard
    public MatchPicture(String setName, User currentPlayer) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(boardWidth, boardHeight));

        statsPanel = new panelStats(boardWidth, currentPlayer.getUsername());
        this.add(statsPanel, BorderLayout.NORTH);

        boardPanel = new panelBoard(restartButton, statsPanel, setName);
        this.add(boardPanel, BorderLayout.CENTER);

        statsPanel.setOnTimeUp(() -> {
            boardPanel.restartGame();
            if (onGameOver != null) {
                statsPanel.stopTimer();
                boardPanel.stopTimer();
                this.currentScore = statsPanel.getScoreCount();
                onGameOver.run();
            }
        });

        this.add(restartGamePanel, BorderLayout.SOUTH);
    }

    public void startGame() {
        statsPanel.startTimer();
        boardPanel.startHide();
    }

    public int getCurrentScore() { return this.currentScore; }
}

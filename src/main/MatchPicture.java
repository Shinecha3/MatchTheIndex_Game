package main;

import java.awt.*;
import javax.swing.*;
import lib.User_Section.User;
import lib.panel.*;

public class MatchPicture extends JPanel {
    private int currentScore = 0;
    private int rows = 4;
    private int columns = 4;
    private int cardwidth = 90;
    private int cardHeight = 90;

    private String mode;
    private int boardWidth = columns * cardwidth;
    private int boardHeight = rows * cardHeight;

    private JButton restartButton = new JButton();
    private JPanel restartGamePanel = new JPanel();

    private panelStats statsPanel;
    private panelBoard boardPanel;

    private Runnable onGameOver;

    public void setOnGameOver(Runnable onGameOver) {
        this.onGameOver = onGameOver;
    }

    // ✅ รับ setName เพื่อส่งต่อให้ panelBoard
    public MatchPicture(User currentPlayer, String mode) {
        this.mode = mode.toLowerCase();
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(boardWidth, boardHeight));

        statsPanel = new panelStats(boardWidth, currentPlayer.getUsername());
        this.add(statsPanel, BorderLayout.NORTH);

        boardPanel = new panelBoard(restartButton, statsPanel, mode);
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

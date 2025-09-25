import java.awt.*;
import javax.swing.*;
import lib.panel.*;

public class MatchPicture extends JPanel {

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

    // 👇 callback เวลาเกมจบ
    private Runnable onGameOver;

    public void setOnGameOver(Runnable onGameOver) {
        this.onGameOver = onGameOver;
    }

    public MatchPicture(String gameType) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(boardWidth, boardHeight));

        statsPanel = new panelStats(boardWidth);
        this.add(statsPanel, BorderLayout.NORTH);

        if (gameType.equals("kuromi")) {

        boardPanel = new panelBoard(restartButton, statsPanel);
        this.add(boardPanel, BorderLayout.CENTER);

        statsPanel.setOnTimeUp(() -> {
            boardPanel.restartGame();
            if (onGameOver != null) {
                onGameOver.run(); // ✅ ไปหน้าถัดไป
            }
        });

        restartButton.setFont(new Font("Arial", Font.PLAIN, 16));
        restartButton.setText("Restart Game");
        restartButton.setPreferredSize(new Dimension(boardWidth, 30));
        restartButton.setFocusable(false);
        restartButton.addActionListener(e -> {
            boardPanel.restartGame();
            statsPanel.updateScore(0);
            statsPanel.startTimer(); // ✅ เริ่มใหม่เมื่อกด Restart
        });

        restartGamePanel.add(restartButton);
        this.add(restartGamePanel, BorderLayout.SOUTH);
        } else if (gameType.equals("math")) {
        panelMath2Math = new panelMath(restartButton, statsPanel);
        this.add(panelMath2Math, BorderLayout.CENTER);

        statsPanel.setOnTimeUp(() -> {
            panelMath2Math.restartGame();
            if (onGameOver != null) {
                onGameOver.run(); // ✅ ไปหน้าถัดไป
            }
        });
        restartButton.setFont(new Font("Arial", Font.PLAIN, 16));
        restartButton.setText("Restart Game");
        restartButton.setPreferredSize(new Dimension(boardWidth, 30));
        restartButton.setFocusable(false);
        restartButton.addActionListener(e -> {
            panelMath2Math.restartGame();
            statsPanel.updateScore(0);
            statsPanel.startTimer(); // ✅ เริ่มใหม่เมื่อกด Restart
        });

        restartGamePanel.add(restartButton);
        this.add(restartGamePanel, BorderLayout.SOUTH);
        }

        // restartButton.setFont(new Font("Arial", Font.PLAIN, 16));
        // restartButton.setText("Restart Game");
        // restartButton.setPreferredSize(new Dimension(boardWidth, 30));
        // restartButton.setFocusable(false);
        // restartButton.addActionListener(e -> {
        //     boardPanel.restartGame();
        //     statsPanel.updateScore(0);
        //     statsPanel.startTimer(); // ✅ เริ่มใหม่เมื่อกด Restart
        // });

        // restartGamePanel.add(restartButton);
        // this.add(restartGamePanel, BorderLayout.SOUTH);
    }

    // 👇 สั่งให้เวลาเริ่มนับหลังเข้าหน้าเกม
    public void startGame() {
        statsPanel.startTimer();
    }
}

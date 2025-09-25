import java.awt.*;
import javax.swing.*;
import lib.panel.*;

public class MainFrame extends JFrame {
    CardLayout cardLayout;
    JPanel cards;

    public MainFrame() {
        this.setTitle("Match Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // หน้าเริ่ม
        panelStart menuPanel = new panelStart();

        // หน้าเกม
        MatchPicture gamePanel = new MatchPicture();

        // หน้า GameOver
        panelOver gameOverPanel = new panelOver();

        // set callback จากหน้าเกม
        gamePanel.setOnGameOver(finalScore -> {
            // gameOverPanel.setFinalScore(finalScore);
            cardLayout.show(cards, "GameOver");
        });

        // ปุ่ม Start → เข้าหน้า Game + เริ่มเกม
        menuPanel.getStartButton().addActionListener(e -> {
            cardLayout.show(cards, "Game");
            gamePanel.restartGame(); // reset และเปิดโชว์ → คว่ำ → เริ่มเวลา
        });

        // ปุ่ม Back to Menu ใน GameOver
        gameOverPanel.getBackToMenuButton().addActionListener(e -> {
            cardLayout.show(cards, "Menu");
        });

        // ปุ่ม Restart Game ใน GameOver
        gameOverPanel.getRestartButton().addActionListener(e -> {
            cardLayout.show(cards, "Game");
            gamePanel.restartGame(); // reset และเริ่มใหม่
        });

        // เพิ่มทุกหน้าเข้าไปใน card
        cards.add(menuPanel, "Menu");
        cards.add(gamePanel, "Game");
        cards.add(gameOverPanel, "GameOver");

        this.add(cards);
        this.setVisible(true);
    }


}

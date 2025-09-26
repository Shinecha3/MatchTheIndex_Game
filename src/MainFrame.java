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

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        UserManager userManager = new UserManager();

        // หน้าเริ่ม
        panelStart menuPanel = new panelStart();

        // หน้าเกม
        MatchPicture gamePanel = new MatchPicture("kuromi");
        MatchPicture gamePanel2 = new MatchPicture("math");

        // หน้า GameOver
        panelOver gameOverPanel = new panelOver();

        //  set callback จากหน้าเกม
        // gamePanel.setOnGameOver(() -> cardLayout.show(cards, "GameOver"));
        gamePanel2.setOnGameOver(() -> cardLayout.show(cards, "GameOver"));

        //  set action ของปุ่ม Start ที่อยู่ใน panelStart
        menuPanel.getStartButton().addActionListener(e -> {
            // userManager.();
            cardLayout.show(cards, "Game");
            gamePanel.startGame();
        });
        menuPanel.getStartButton2().addActionListener(e -> {
            cardLayout.show(cards, "Game2");
            gamePanel2.startGame();
        });

        //  set action ของปุ่ม Back to Menu ที่อยู่ใน panelGameOver
        gameOverPanel.getBackToMenuButton().addActionListener(e -> {
            cardLayout.show(cards, "Menu");
        });

        //  set action ของปุ่ม Restart Game ที่อยู่ใน panelGameOver
        gameOverPanel.getRestartButton().addActionListener(e -> {
            cardLayout.show(cards, "Game");
            gamePanel.startGame(); // เริ่มเกมใหม่ทันที
        });

        // เพิ่มทุกหน้าเข้าไปใน card
        cards.add(menuPanel, "Menu");
        cards.add(gamePanel, "Game");
        cards.add(gamePanel2, "Game2");
        cards.add(gameOverPanel, "GameOver");

        this.add(cards);
        this.setVisible(true);
    }


}

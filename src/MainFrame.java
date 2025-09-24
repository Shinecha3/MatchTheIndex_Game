import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    CardLayout cardLayout;
    JPanel cards;

    public void Initail(){
        this.setTitle("Match Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(390, 480);
        this.setLocationRelativeTo(null);
    }

    public void Finally(){
        this.add(cards);
        this.setVisible(true);
    }

    public MainFrame() {
        Initail();

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // หน้าแรก (Menu)
        JPanel menuPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Welcome to Kuromi Match!", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        menuPanel.add(title, BorderLayout.CENTER);

        // ✅ สร้างหน้าเกมไว้ตรงนี้
        MatchPicture gamePanel = new MatchPicture();

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> {
            cardLayout.show(cards, "Game");
            gamePanel.startGame();   // ✅ Timer จะเริ่มนับเมื่อเข้าหน้าเกม
        });
        menuPanel.add(startButton, BorderLayout.SOUTH);

        // หน้า 3 (Game Over)
        JPanel gameOverPanel = new JPanel(new BorderLayout());
        JLabel gameOverLabel = new JLabel("Game Over!", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 22));
        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);

        JButton backToMenu = new JButton("Back to Menu");
        backToMenu.addActionListener(e -> cardLayout.show(cards, "Menu"));
        gameOverPanel.add(backToMenu, BorderLayout.SOUTH);

        // ✅ set callback ให้ MatchPicture
        gamePanel.setOnGameOver(() -> cardLayout.show(cards, "GameOver"));

        // ใส่ทุกหน้า
        cards.add(menuPanel, "Menu");
        cards.add(gamePanel, "Game");
        cards.add(gameOverPanel, "GameOver");

        Finally();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

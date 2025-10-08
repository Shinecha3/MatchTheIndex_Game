package lib.panel;

import java.awt.*;
import javax.swing.*;

public class panelOver extends JPanel {

    private JButton backToMenuButton;
    private JButton restartButton;
    private int currentScore;
    JLabel gameOverLabel;
    public panelOver() {
        this.setLayout(new BorderLayout());

        gameOverLabel = new JLabel("Game Over! You Get "+ currentScore , SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 22));
        this.add(gameOverLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        backToMenuButton = new JButton("Back to Menu");
        restartButton = new JButton("Restart Game");

        buttonPanel.add(backToMenuButton);
        buttonPanel.add(restartButton);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    //  getters สำหรับให้ MainFrame เอาไปใส่ ActionListener
    public JButton getBackToMenuButton() {
        return backToMenuButton;
    }
    
    public void setCurrentScore(int currentScore){
        this.currentScore = currentScore;
        gameOverLabel.setText("Game Over! You Get "+ currentScore);
    }

    public JButton getRestartButton() {
        return restartButton;
    }
}

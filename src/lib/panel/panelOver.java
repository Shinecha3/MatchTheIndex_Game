package lib.panel;

import java.awt.*;
import javax.swing.*;

public class panelOver extends JPanel {

    private JButton backToMenuButton;
    private JButton restartButton;
    private int currentScore;
    private int highestScore;
    JLabel gameOverLabel;
    public panelOver() {
        this.setLayout(new BorderLayout());

        JPanel scoreboardPanel = new JPanel();
        scoreboardPanel.setLayout(new BoxLayout(scoreboardPanel, BoxLayout.Y_AXIS));
        scoreboardPanel.add(new JLabel("Alice - 1200"));
        scoreboardPanel.add(new JLabel("Bob - 950"));


        gameOverLabel = new JLabel("Game Over! You Get "+ currentScore , SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 22));
        
        this.add(scoreboardPanel, BorderLayout.NORTH);
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

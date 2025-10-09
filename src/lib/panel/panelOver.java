package lib.panel;
import lib.User_Section.*;
import java.awt.*;
import javax.swing.*;
import java.util.List;

public class panelOver extends JPanel {

    private JButton backToMenuButton;
    private JButton restartButton;
    private int currentScore;
    private int highestScore;

    JLabel gameOverLabel;
    JLabel highestScoreLabel;
    
    public panelOver(List<User> ranking) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel scoreboardPanel = new JPanel();
        scoreboardPanel.setLayout(new BoxLayout(scoreboardPanel, BoxLayout.Y_AXIS));
        scoreboardPanel.add(new JLabel("Alice - 1200"));
        scoreboardPanel.add(new JLabel("Bob - 950"));


        gameOverLabel = new JLabel("Game Over! You Get "+ currentScore , SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 22));
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        highestScoreLabel = new JLabel("Your HightestScore : "+ highestScore , SwingConstants.CENTER);
        highestScoreLabel.setFont(new Font("Arial", Font.BOLD, 25));
        highestScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalStrut(50));
        this.add(scoreboardPanel);
        this.add(Box.createVerticalStrut(50));
        this.add(highestScoreLabel);
        this.add(Box.createVerticalStrut(50));
        this.add(gameOverLabel);
        this.add(Box.createVerticalStrut(50));



        JPanel buttonPanel = new JPanel();
        backToMenuButton = new JButton("giveUp?");
        backToMenuButton.setPreferredSize(new Dimension(160,40));

        restartButton = new JButton("Restart Game");
        restartButton.setPreferredSize(new Dimension(160,60));

        buttonPanel.add(backToMenuButton);
        buttonPanel.add(restartButton);


        this.add(buttonPanel);
    }

    //  getters สำหรับให้ MainFrame เอาไปใส่ ActionListener
    public JButton getBackToMenuButton() {
        return backToMenuButton;
    }
    
    public void setCurrentScore(int currentScore){
        this.currentScore = currentScore;
        if (currentScore < highestScore) {
            gameOverLabel.setText("Really ? Score: "+ currentScore);
        }else{
            gameOverLabel.setText("You made NewHigh! Score: "+ currentScore);
        }
        
    }

    public void setHightestScore(int highestScore, int currentScore){
        this.highestScore = highestScore;
        this.currentScore = currentScore;
        if (currentScore > highestScore) {
            highestScoreLabel.setText("Your HightestScore : "+ currentScore);
        }else{
            highestScoreLabel.setText("Your HightestScore : "+ highestScore);
        }
    }

    public JButton getRestartButton() {
        return restartButton;
    }


}

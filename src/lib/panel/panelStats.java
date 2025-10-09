package lib.panel;
import javax.swing.*;
import java.awt.*;
import lib.*;

public class panelStats extends JPanel {
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JLabel PlayernameLabel;

    private int scoreCount = 0;
    private int time = 10;
    private int timeLeft = time;  
    private Timer gameTimer;

    private Runnable onTimeUp;
    public panelStats(int boardWidth,String playerName) {
        setPreferredSize(new Dimension(boardWidth, 30));
        setLayout(new GridLayout(1, 3)); 

        PlayernameLabel = new JLabel(playerName,SwingConstants.CENTER);
        PlayernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        timeLabel = new JLabel("Time: " + timeLeft, SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        
        add(PlayernameLabel);
        add(scoreLabel);
        add(timeLabel);

        //  Timer สำหรับนับถอยหลัง
        gameTimer = new Timer(1000, e -> {
            timeLeft--;
            timeLabel.setText("Time: " + timeLeft);

            if (timeLeft <= 0) {
                if (onTimeUp != null) {
                    onTimeUp.run(); // ✅ เรียก action จาก panelBoard/MatchPicture
                    scoreCount = 0;
                    scoreLabel.setText("Score: 0");
                }
            }
        });
    }

    //  อัปเดตค่า Error
    public void updateScore(int count) {
        this.scoreCount = count;
        scoreLabel.setText("Score: " + scoreCount);
    }

    //  จัดการ Timer
    public void startTimer() {
        timeLeft = time;
        timeLabel.setText("Time: " + timeLeft);
        gameTimer.start();
    }

    public void stopTimer() {
        gameTimer.stop();
    }

    public void resetTimer() {
        stopTimer();
        timeLeft = time;
        timeLabel.setText("Time: " + timeLeft);
    }

    public void setOnTimeUp(Runnable r) {
        this.onTimeUp = r;
    }

    public int getScoreCount(){return this.scoreCount;}
}

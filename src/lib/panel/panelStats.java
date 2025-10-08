package lib.panel;

import javax.swing.*;
import java.awt.*;

public class panelStats extends JPanel {
    private JLabel scoreLabel;
    private JLabel timeLabel;

    private int scoreCount = 0;
    private int time = 30;
    private int timeLeft = time;  
    private Timer gameTimer;

    private Runnable onTimeUp;
    public panelStats(int boardWidth) {
        setPreferredSize(new Dimension(boardWidth, 30));
        setLayout(new GridLayout(1, 2)); // แสดง 2 ช่อง: Error | Time

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        timeLabel = new JLabel("Time: " + timeLeft, SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        add(scoreLabel);
        add(timeLabel);

        //  Timer สำหรับนับถอยหลัง
        gameTimer = new Timer(1000, e -> {
            timeLeft--;
            timeLabel.setText("Time: " + timeLeft);

            if (timeLeft <= 0) {
                if (onTimeUp != null) {
                    onTimeUp.run(); // ✅ เรียก action จาก panelBoard/MatchPicture
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
}

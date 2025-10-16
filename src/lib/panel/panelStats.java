package lib.panel;
import javax.swing.*;
import java.awt.*;
import java.sql.Time;

public class panelStats extends JPanel {
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JLabel PlayernameLabel;

    private int scoreCount = 0;
    private int time = 30;
    private int timeLeft = time;  
    private Timer gameTimer;
    
    int popTime=0;

    private Runnable onTimeUp;
    public panelStats(int boardWidth,String playerName) {
        setPreferredSize(new Dimension(boardWidth, 50));
        setLayout(new GridLayout(1, 3)); 
        this.setOpaque(false);
        PlayernameLabel = new JLabel(playerName,SwingConstants.CENTER);
        PlayernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        PlayernameLabel.setForeground(Color.WHITE);
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        scoreLabel.setForeground(Color.WHITE);

        timeLabel = new JLabel("Time: " + timeLeft, SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timeLabel.setForeground(Color.WHITE);
        
        add(PlayernameLabel);
        add(scoreLabel);
        add(timeLabel);

        //  Timer สำหรับนับถอยหลัง
        gameTimer = new Timer(1000, e -> {
            timeLeft--;
            if (popTime != 0) {
                if (popTime > 0) {
                    timeLabel.setText("Time: " + timeLeft + " +" + popTime);
                }else{ timeLabel.setText("Time: " + timeLeft + " " + popTime); }

            }else{
                timeLabel.setText("Time: " + timeLeft); 
                
            }
            
            popTime = 0;
            if (timeLeft <= 0) {
                if (onTimeUp != null) {
                    onTimeUp.run(); //  เรียก action จาก panelBoard/MatchPicture
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

    public void addTime(int sec) {
        this.timeLeft += sec;
        popTime = sec;
    }

    public int getScoreCount(){return this.scoreCount;}
}
